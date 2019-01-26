package site.keyu;

import site.keyu.DFA.SensitiveWordFilter;

public class Main {

    public static void main(String[] args) {
        SensitiveWordFilter sensitiveWordFilter = new SensitiveWordFilter();
        System.out.println(sensitiveWordFilter.filter("你个大猪蹄子，大猪肘子，我草泥马，草泥马"));
    }
}
