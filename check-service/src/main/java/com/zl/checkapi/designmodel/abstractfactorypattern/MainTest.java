package com.zl.checkapi.designmodel.abstractfactorypattern;

/**
 * @desc 抽象工厂模式
 */
public class MainTest {
    public static void main(String[] args) {
        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        if(colorFactory != null){
            Color color = colorFactory.getColor("red");
            if(color!=null){
                color.fill();
            }
        }
    }
}
