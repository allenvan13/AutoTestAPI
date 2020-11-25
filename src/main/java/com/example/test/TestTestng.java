package com.example.test;


import org.testng.Reporter;
import org.testng.annotations.Test;

@Test(description = "类描述")
public class TestTestng {

    @Test(description = "方法描述2")
    public void Test002(){
        Reporter.log("步骤2");
        System.out.println("进来了2。。。。。。。。。。。");
    }

    @Test(description = "方法描述3")
    public void Test003(){
        Reporter.log("步骤3");
        System.out.println("进来了3。。。。。。。。。。。");
    }

    @Test(description = "方法描述4")
    public void Test004(){
        Reporter.log("步骤4");
        System.out.println("进来了4。。。。。。。。。。。");
    }

    @Test(description = "方法描述5")
    public void Test005(){
        Reporter.log("步骤5");
        System.out.println("进来了5。。。。。。。。。。。");
    }

    @Test(description = "方法描述6")
    public void Test006(){
        Reporter.log("步骤6");
        System.out.println("进来了6。。。。。。。。。。。");
    }

    @Test(description = "方法描述7")
    public void Test007(){
        Reporter.log("步骤7");
        System.out.println("进来了7。。。。。。。。。。。");
    }

    @Test(description = "方法描述8")
    public void Test008(){
        Reporter.log("步骤8");
        System.out.println("进来了8。。。。。。。。。。。");
    }

    @Test(description = "方法描述9")
    public void Test009(){
        Reporter.log("步骤9");
        System.out.println("进来了9。。。。。。。。。。。");
    }
}
