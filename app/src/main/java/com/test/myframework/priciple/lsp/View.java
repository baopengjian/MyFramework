package com.test.myframework.priciple.lsp;

/**
 * 里氏替换：LSP 所有应用类的地方必须能透明使用其子类的对象
 */
public abstract class View {

   public abstract void draw();

   public void measure(int width,int height){

   }

}
