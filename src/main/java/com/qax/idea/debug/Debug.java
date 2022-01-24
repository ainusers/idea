package com.qax.idea.debug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.*;

/**
 * 项目名称: cc
 * 文件名称: com.example.cc
 * 描述: [功能描述]
 * 创建时间: 2022/1/10.
 * 公司信息: Qi An Xin Group.Situation 态势感知事业部
 *
 * @author tianyong@qianxin.com
 * @version v2.0
 */
@Controller
public class Debug {

    class Flow {
        public Process beginProcess;
        public String status;
        public String flows;
        public Process getNextProcess(Process process) {
            if(process instanceof Process1){
                process = new Process2();
            }else if(process instanceof Process2){
                process = new Process3();
            }else if(process instanceof Process3){
                process = new Process1();
            }
            return process;
        }
        @Override
        public String toString() {
            return "Flow{" +
                    "beginProcess=" + beginProcess +
                    ", status='" + status + '\'' +
                    ", flows='" + flows + '\'' +
                    '}';
        }
    }
    abstract class Process implements Cloneable {
        public Process next;

        abstract void run(Context context);
    }
    class Process1 extends Process {
        public void run(Context context) {
            System.out.println(List.of("process1"));
            context.data = "测试数据1";
        }
    }
    class Process2 extends Process {
        public void run(Context context) {
            System.out.println(List.of("process2"));
            context.data = "测试数据2";
        }
    }
    class Process3 extends Process {
        public void run(Context context) {
            System.out.println(List.of("process3"));
            context.data = "测试数据3";
        }
    }
    class Context implements Cloneable{
        public Process currProcess;
        public Flow flow;
        public String data;

        public Context init() throws Exception {
            Flow flow = new Flow();
            flow.status = "running";
            flow.flows = "Process1,Process2,Process3";
            flow.beginProcess = new Process1();
            this.currProcess = flow.beginProcess;
            this.flow = flow;
            this.data = "测试数据";
            return this;
        }
        public Context step() {
            currProcess.run(this);
            currProcess = flow.getNextProcess(currProcess);
            return this;
        }
        @Override
        public String toString() {
            return "Context{" +
                    "currProcess=" + currProcess +
                    ", flow=" + flow +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
    public void entry(String cmd, Context context) throws Exception {
        Callable<Context> call = null;
        ExecutorService exec = Executors.newSingleThreadExecutor();
        switch (cmd) {
            case "step":
                call = () -> context.step();
                break;
        }
        Future<Context> future = exec.submit(call);
        Cloneable cloneable = future.get(30L, TimeUnit.SECONDS);
        System.out.println(cloneable);
    }

    final Context context = new Context();

    @ResponseBody
    @RequestMapping("/step")
    public void step() throws Exception {
        // CountedBlockQueue<Cloneable> cbq= new CountedBlockQueue(new ArrayBlockingQueue(5));
        new Debug().entry("step",context);
    }

    @ResponseBody
    @RequestMapping("/init")
    public Context init() throws Exception {
        Context init = context.init();
        return init;
    }

}
