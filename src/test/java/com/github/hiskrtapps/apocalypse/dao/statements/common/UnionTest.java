/*
 * © 2020 Ceppi Productions.
 */
package com.github.hiskrtapps.apocalypse.dao.statements.common;

import com.github.hiskrtapps.apocalypse.dao.Entity;
import com.github.hiskrtapps.apocalypse.dao.statements.Find;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.tuple.Pair.of;

/**
 * Test of the Union finder
 */
public class UnionTest {

    private TestFind find1, find2, find3;

    @Before
    public void setup() {
        find1 = new TestFind("command1", "name1", createMap(of("v1", "val1")));
        find2 = new TestFind("command2", "name2", createMap(of("v2", "val2"), of("v3", "val3")));
        find3 = new TestFind("command3", "name3", createMap(of("v4", "val4")));
    }

    private Map<String, Object> createMap(Pair<String, String>... values) {
        Map<String, Object> valueMap = new HashMap<>();

        for (Pair<String, String> value : values) {
            valueMap.put(value.getLeft(), value.getRight());
        }

        return valueMap;
    }

    @Test
    public void entityClass() {
        Union<TestEntity> union = new Union(true, find1, find2, find3);
        Assert.assertEquals(TestEntity.class, union.entityClass());
    }

    @Test
    public void valuesMap() {
        Union<TestEntity> union = new Union(true, find1, find2, find3);
        Map<String, Object> valuesMap = union.valuesMap();
        // the valuesMap should be the union of all values map of each find
        Assert.assertEquals(4, valuesMap.size());
        for (int i = 1; i <= 4; ++i) {
            Assert.assertEquals("val" + i, valuesMap.get("v" + i));
        }
    }

    @Test
    public void name() {
        Union<TestEntity> union = new Union(true, find1, find2, find3);
        String name = union.name();
        // just check that all names are found, whatever the format of the string
        Assert.assertTrue(name.contains(find1.name));
        Assert.assertTrue(name.contains(find2.name));
        Assert.assertTrue(name.contains(find3.name));
    }

    @Test
    public void commandSeveralFindUnionAll() {
        Union<TestEntity> union = new Union(true, find1, find2, find3);
        String command = union.command();
        Assert.assertEquals("(" + find1.command + ") union all ("
                + find2.command + ") union all ("
                + find3.command + ")", command);
    }

    @Test
    public void commandSeveralFindUnion() {
        Union<TestEntity> union = new Union(false, find1, find2, find3);
        String command = union.command();
        Assert.assertEquals("(" + find1.command + ") union ("
                + find2.command + ") union ("
                + find3.command + ")", command);
    }

    @Test
    public void commandOneFind() {
        Union<TestEntity> union = new Union(true, find1);
        String command = union.command();
        Assert.assertEquals("(" + find1.command + ")", command);
    }

    private static class TestFind implements Find<TestEntity> {
        private String command;
        private String name;
        private Map<String, Object> valuesMap;

        public TestFind(String command, String name, Map<String, Object> valuesMap) {
            this.command = command;
            this.name = name;
            this.valuesMap = valuesMap;
        }

        @Override
        public Class<TestEntity> entityClass() {
            return TestEntity.class;
        }

        @Override
        public Map<String, Object> valuesMap() {
            return valuesMap;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String command() {
            return command;
        }
    }

    private static class TestEntity implements Entity {
        @Override
        public <T> T get(String columnName) {
            return null;
        }

        @Override
        public void set(String columnName, Object columnValue) {
        }

        @Override
        public void primaryKey(Object... values) {
        }
    }
}