package de.fh.kiel.advancedjava.pojomodel.facade;

import org.springframework.stereotype.Service;

@Service
public class Util {

    protected String parsePackageName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(0, completePath.lastIndexOf("."));
        return completePath;
    }

    protected String parseClassName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(completePath.lastIndexOf(".") + 1);
        return completePath;
    }


}

