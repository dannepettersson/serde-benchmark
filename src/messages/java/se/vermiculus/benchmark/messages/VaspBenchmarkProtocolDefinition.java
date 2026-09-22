package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.VaspProtocolDefinition;

public class VaspBenchmarkProtocolDefinition extends VaspProtocolDefinition {
  public static final int ADDRESS = 1;
  public static final int CATEGORY = 2;
  public static final int CUSTOMER = 3;
  public static final int ORDER = 4;
  public static final int ORDER_LINE = 5;
  public static final int PRODUCT = 6;

  @Override
  protected void defineProtocol() {
    addEntity(Address.class);
    addEntity(Category.class);
    addEntity(Customer.class);
    addEntity(Order.class);
    addEntity(OrderLine.class);
    addEntity(Product.class);
  }
}
