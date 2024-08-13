package com.kkcf.commonsio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        File src = new File("Day29/src/com/kkcf/commonsio/a.txt");
        File dest = new File("Day29/src/com/kkcf/commonsio/b.txt");
        FileUtils.copyFile(src, dest);
    }
}
