package com.example.Giang.model;

public class Function {
    int functionThumb;
    String functionName;

    public Function(int functionThumb, String functionName) {
        this.functionThumb = functionThumb;
        this.functionName = functionName;
    }

    public int getFunctionThumb() {
        return functionThumb;
    }

    public void setFunctionThumb(int functionThumb) {
        this.functionThumb = functionThumb;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
