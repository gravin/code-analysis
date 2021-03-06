package com.codeanalysis.jdk.miniature.streamusage.stream;

import com.codeanalysis.jdk.miniature.streamusage.function.EvalFunction;

/**
 * @Author xiongyx
 * @Date 2019/3/6
 *
 * 下一个元素求值过程
 */
public class NextItemEvalProcess {

    /**
     * 求值方法
     * */
    private EvalFunction evalFunction;

    public NextItemEvalProcess(EvalFunction evalFunction) {
        this.evalFunction = evalFunction;
    }

    MyStream eval(){
        return evalFunction.apply();
    }
}
