package com.hubspot.jinjava.el.ext;

import de.odysseus.el.misc.TypeConverter;
import de.odysseus.el.tree.impl.Parser.ExtensionHandler;
import de.odysseus.el.tree.impl.Parser.ExtensionPoint;
import de.odysseus.el.tree.impl.Scanner;
import de.odysseus.el.tree.impl.ast.AstBinary;
import de.odysseus.el.tree.impl.ast.AstBinary.SimpleOperator;
import de.odysseus.el.tree.impl.ast.AstNode;

public class RegularDivOperator extends SimpleOperator {
  public static final Scanner.ExtensionToken TOKEN = new Scanner.ExtensionToken("/");
  public static final RegularDivOperator OP = new RegularDivOperator();

  @Override
  protected Object apply(TypeConverter converter, Object a, Object b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Division argument may not be null");
    }

    Number numA = (Number) a;
    Number numB = (Number) b;
    if (numB.doubleValue() == 0.0) {
      throw new IllegalArgumentException("Divisor may not be zero");
    }

    return numA.doubleValue() / numB.doubleValue();
  }

  public static final ExtensionHandler HANDLER = new ExtensionHandler(
    ExtensionPoint.MUL
  ) {

    @Override
    public AstNode createAstNode(AstNode... children) {
      return new AstBinary(children[0], children[1], OP);
    }
  };
}
