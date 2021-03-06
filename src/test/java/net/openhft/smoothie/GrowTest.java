/*
 *    Copyright 2015, 2016 Chronicle Software
 *    Copyright 2016, 2018 Roman Leventov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package net.openhft.smoothie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GrowTest {

    SmoothieMap<Integer, Integer> map = new SmoothieMap<>();
    List<Integer> keys = new ArrayList<>();
    Random random = new Random(42);

    @Before
    public void fillMap() {
        for (int i = 0; i < 10_000_000; i++) {
            int key = random.nextInt();
            keys.add(key);
            Integer res = map.put(key, 0);
            assertTrue(res == null || res == 0);
        }
    }

    @Test
    public void growTest() {
        for (int i = 0; i < keys.size(); i++) {
            Integer key = keys.get(i);
            assertEquals((Integer) 0, map.get(key));
        }
    }

    @Test
    public void testClone() {
        SmoothieMap<Integer, Integer> clone = map.clone();
        assertEquals(map, clone);
    }
}
