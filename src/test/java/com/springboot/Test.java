package com.springboot;

import com.alibaba.fastjson.JSONObject;
import com.springboot.D2.User;
import com.springboot.util.ClassFieldChangeUtil;

import java.util.*;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 2018/6/14
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setAge(12);
        user.setName("yyy");
        User user1 = new User();
        user1.setId(1L);
        user1.setAge(12);
        user1.setName("ttt");

        Map<String, String> compareMap = new HashMap<>();
        compareMap.put("name", "");
        Map<String, Object> result = ClassFieldChangeUtil.getModifyContent(user, user1, compareMap);
        System.out.println(JSONObject.toJSONString(result));
        Map map = new HashMap<>();
        map.put("abc", "测试");
        if (null != map) {
            Object ss = map.get("abc");
            if (null != ss) {
                System.out.println(String.valueOf(ss));
            }
        }
        map.remove("abc");

        Optional<Map> kk = Optional.of(map);
        kk.isPresent();
        kk.map(u -> u.get("abc"))
                .map(o -> String.valueOf(o))
                .ifPresent(System.out::println);

        getString(10);

        List<Map> listMap = new ArrayList<>();
        listMap.stream().forEach(ps -> System.out.println(ps.size()));

    }

    public static void getString(int i) {
        char[] chars = Integer.toBinaryString(i).toCharArray();
        for (int k = 0; k < chars.length; k++) {
            if ('1' == chars[k]) {
                System.out.println(k);
            }
        }
    }
}
