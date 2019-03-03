# Bad-Isometric-Engine
A not-actually-isometric isometric view rendering engine (outputs in monospace text)

| <pre>var view = new isoview(10);<br> view.protectedDraw((x, y) -> {<br>   return x == 5 && y == 4;<br> }, SMILE);<br> view.protectedDraw((x, y) -> {<br>   return x == 5 && y == 5;<br> }, TABLE);<br> for (int i = 0; i < view.getSize(); i++) {<br>   System.out.println(view.getLine(i));<br> }</pre> | <pre>     /\    <br>    //\\   <br>   //[[\\  <br>  //[[[[\\ <br> //[[[😊[\\<br> [[[[[┬─┬[[<br>  [[[[[[[[ <br>   [[[[[[  <br>    [[[[   <br>     [[    <br> </pre> |
|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|

Thanks [Markdown Tables Generator](https://www.tablesgenerator.com/markdown_tables#)⬆️⬆️⬆️
