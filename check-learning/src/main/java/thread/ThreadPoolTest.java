package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lenovo
 */
public class ThreadPoolTest {
    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);
    private static class NumberWraper{
        private volatile int value = 0;
    }
    public static void main(String[] args) throws InterruptedException{
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newIOPool("test-pool");
        NumberWraper numberWraper = new NumberWraper();
        for(int i=0;i<20000;i++){
            threadPoolExecutor.execute(()->{
                synchronized (numberWraper){
                    numberWraper.value++;
                }
                logger.info("thread {} executing 第{}次", Thread.currentThread().getName(), numberWraper.value);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //关闭接收任务，但队列里面的任务会继续处理
        threadPoolExecutor.shutdown();
        do{
            logger.info("waiting thread finished, the size of queue is => {}", threadPoolExecutor.getQueue().size());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (threadPoolExecutor.getQueue().size()>0);

        //等待线程池里面的任务全部处理完成
        long taskCount ;
        long taskCompletedCount;

        do{
            taskCount = threadPoolExecutor.getTaskCount();
            taskCompletedCount = threadPoolExecutor.getCompletedTaskCount();
            logger.info("task count is => {}", taskCount);
            logger.info("completed task count is => {}", taskCompletedCount);
            TimeUnit.MILLISECONDS.sleep(1);
        }while (taskCount!=taskCompletedCount);

        logger.info("all of threads is finished");
    }
}
