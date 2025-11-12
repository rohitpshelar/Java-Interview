package com.rs;

import java.io.*;
import java.net.*;
import java.util.*;

class ApiResponseParser {

    public static void main(String[] args) {
        List<String> shows = showsInProduction(-1, -1);
        for (String show : shows) {
            System.out.println(show);
        }
    }

    public static List<String> showsInProduction(int startYear, int endYear) {
        List<String> result = new ArrayList<>();
        int page = 1;
        int totalPages = 1;

        try {
            do {
                String urlStr = "https://jsonmock.hackerrank.com/api/tvseries?page=" + page;
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                conn.disconnect();

                String response = content.toString();

                // Parse total_pages
                totalPages = extractIntValue(response, "total_pages");

                // Extract data array using a more robust method
                List<String> shows = extractAllShows(response);

                for (String showStr : shows) {
                    String name = extractStringValue(showStr, "name");
                    String runtimeOfSeries = extractStringValue(showStr, "runtime_of_series");

                    if (name == null || runtimeOfSeries == null) continue;

                    // Parse the runtime_of_series field
                    ParsedYears years = parseRuntime(runtimeOfSeries);
                    if (years == null) continue;

                    // Apply filtering logic
                    if (endYear == -1) {
                        // Looking for shows still in production that started in/after startYear
                        if (years.stillProducing && years.start >= startYear) {
                            result.add(name);
                        }
                    } else {
                        // Looking for shows that ended by endYear and started in/after startYear
                        if (!years.stillProducing && years.end != null &&
                                years.start >= startYear && years.end <= endYear) {
                            result.add(name);
                        }
                    }
                }

                page++;
            } while (page <= totalPages);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(result);
        return result;
    }

    private static ParsedYears parseRuntime(String runtime) {
        if (runtime == null || runtime.trim().isEmpty()) {
            return null;
        }

        // Remove (i), (ii) prefixes
        String cleaned = runtime.replaceAll("^\\([i]+\\)\\s*", "").trim();

        // Pattern 1: (YYYY-) - still in production
        if (cleaned.matches("\\(\\d{4}-\\)")) {
            try {
                int start = Integer.parseInt(cleaned.substring(1, 5));
                return new ParsedYears(start, null, true);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        // Pattern 2: (YYYY-YYYY) - ended
        else if (cleaned.matches("\\(\\d{4}-\\d{4}\\)")) {
            try {
                int start = Integer.parseInt(cleaned.substring(1, 5));
                int end = Integer.parseInt(cleaned.substring(6, 10));
                return new ParsedYears(start, end, false);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        // Pattern 3: (YYYY) - single year
        else if (cleaned.matches("\\(\\d{4}\\)")) {
            try {
                int start = Integer.parseInt(cleaned.substring(1, 5));
                return new ParsedYears(start, start, false);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        // Pattern 4: More flexible parsing for edge cases
        else if (cleaned.startsWith("(") && cleaned.contains("-") && cleaned.endsWith(")")) {
            try {
                String yearPart = cleaned.substring(1, cleaned.length() - 1);
                String[] parts = yearPart.split("-");
                if (parts.length == 2) {
                    int start = Integer.parseInt(parts[0].trim());
                    if (parts[1].trim().isEmpty()) {
                        // (YYYY-) format
                        return new ParsedYears(start, null, true);
                    } else {
                        // (YYYY-YYYY) format
                        int end = Integer.parseInt(parts[1].trim());
                        return new ParsedYears(start, end, false);
                    }
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    private static List<String> extractAllShows(String json) {
        List<String> shows = new ArrayList<>();
        int dataIndex = json.indexOf("\"data\":[");
        if (dataIndex == -1) return shows;

        int start = dataIndex + 8; // After "\"data\":["
        int braceCount = 0;
        int currentStart = -1;

        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                braceCount++;
                if (braceCount == 1) {
                    currentStart = i;
                }
            } else if (c == '}') {
                braceCount--;
                if (braceCount == 0 && currentStart != -1) {
                    shows.add(json.substring(currentStart, i + 1));
                    currentStart = -1;
                }
            } else if (c == ']' && braceCount == 0) {
                break; // End of data array
            }
        }

        return shows;
    }

    private static String extractStringValue(String json, String field) {
        String pattern = "\"" + field + "\":\"";
        int fieldIndex = json.indexOf(pattern);
        if (fieldIndex == -1) {
            // Try with space after colon
            pattern = "\"" + field + "\": \"";
            fieldIndex = json.indexOf(pattern);
            if (fieldIndex == -1) return null;
        }

        int valueStart = fieldIndex + pattern.length();
        int valueEnd = valueStart;

        // Find the closing quote, handling escaped quotes
        while (valueEnd < json.length()) {
            if (json.charAt(valueEnd) == '"' && (valueEnd == valueStart || json.charAt(valueEnd - 1) != '\\')) {
                break;
            }
            valueEnd++;
        }

        if (valueEnd >= json.length()) return null;

        String value = json.substring(valueStart, valueEnd);
        // Handle escaped characters
        value = value.replace("\\\"", "\"").replace("\\\\", "\\");
        return value;
    }

    private static int extractIntValue(String json, String field) {
        String pattern = "\"" + field + "\":";
        int fieldIndex = json.indexOf(pattern);
        if (fieldIndex == -1) return 1;

        int valueStart = fieldIndex + pattern.length();
        // Skip any spaces
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }

        int valueEnd = valueStart;
        while (valueEnd < json.length() &&
                (Character.isDigit(json.charAt(valueEnd)) || (valueEnd == valueStart && json.charAt(valueEnd) == '-'))) {
            valueEnd++;
        }

        try {
            return Integer.parseInt(json.substring(valueStart, valueEnd));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    static class ParsedYears {
        int start;
        Integer end;
        boolean stillProducing;

        ParsedYears(int start, Integer end, boolean stillProducing) {
            this.start = start;
            this.end = end;
            this.stillProducing = stillProducing;
        }
    }
}