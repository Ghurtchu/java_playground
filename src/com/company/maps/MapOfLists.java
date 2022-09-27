package com.company.maps;

import java.util.*;

public class MapOfLists {

    static Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] args) {

        List<Data> source = Arrays.asList(new Data("key1", "value1"), new Data("key1", "value2"), new Data("key2", "value3"), new Data("key2", "value4"), new Data("key3", "value5"));

        source.forEach(record -> map.computeIfAbsent(record.key, key -> new ArrayList<>()).add(record.value));

        assert(map.get("key1").equals(List.of("value1", "value2")));
        assert(map.get("key2").equals(List.of("value3", "value4")));
        assert(map.get("key3").equals(List.of("value5")));

        System.out.println(map);

    }

    private static class Data {
        String key;
        String value;

        public Data(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }


}
