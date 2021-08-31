package com.qax.idea.chainInvoke;

import java.io.Serializable;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:47
 * @description:
 * @Version: v1.0
 * @company: Qi An Xin Group.Situation 态势感知事业部
 */
@FunctionalInterface
public interface AbsFunction<I,O> extends Serializable {

    O map(I var1) throws Exception;

}
