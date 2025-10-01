package com.arqui_web.tp_integrador2;

import com.arqui_web.tp_integrador2.service.ConnectionFactory;

public class App {
  public static void main(String[] args) {
    ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
  }
}
