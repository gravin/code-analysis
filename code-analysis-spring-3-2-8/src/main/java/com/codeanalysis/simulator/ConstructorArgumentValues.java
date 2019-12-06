package com.codeanalysis.simulator;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConstructorArgumentValues {

    private final Map<Integer, ValueHolder> indexedArgumentValues = new LinkedHashMap<Integer, ValueHolder>(0);

    private final List<ValueHolder> genericArgumentValues = new LinkedList<ValueHolder>();

    public Map<Integer, ValueHolder> getIndexedArgumentValues() {
        return indexedArgumentValues;
    }

    public List<ValueHolder> getGenericArgumentValues() {
        return genericArgumentValues;
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public void addIndexedArgumentValue(int index, ValueHolder newValue) {
        this.indexedArgumentValues.put(index, newValue);
    }

    public void addGenericArgumentValue(Object value) {
        this.genericArgumentValues.add(new ValueHolder(value));
    }

    public static class ValueHolder {

        private Object value;

        private String type;

        private String name;

        private Object source;

        private boolean converted = false;

        private Object convertedValue;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }

        public boolean isConverted() {
            return converted;
        }

        public void setConverted(boolean converted) {
            this.converted = converted;
        }

        public Object getConvertedValue() {
            return convertedValue;
        }

        public void setConvertedValue(Object convertedValue) {
            this.convertedValue = convertedValue;
        }
    }
}
