package codigo;
import static codigo.Tokens.*;

//definicion de clases y tipos por medio de expresiones regulares y string para retorno del lexema
%%
%class Lexer
%type Tokens
Letras=[a-zA-Z_]+
Digitos=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%
//definicion de palabras reservadas
wdh_int | 
wdh_double |
wdh_float |
wdh_long |
wdh_bool |
wdh_string |
wdh_funtion |
wdh_return |
wdh_if |
wdh_else |
wdh_array |
wdh_while |
wdh_do |
wdh_switch |
wdh_case |
wdh_default |
wdh_break |
wdh_for |
wdh_class |
wdh_const |
wdh_void |
wdh_exit |
wdh_new |
wdh_struct |
wdh_include 
{lexeme=yytext(); return Reservadas;}

//definicion de caracteres a ignorar
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"INICIO" {/*Ignore*/}

//definicion de operaciones aritmeticas
"=" {lexeme=yytext(); return opIgual;}
"+" {lexeme=yytext(); return opSuma;}
"-" {lexeme=yytext(); return opResta;}
"*" {lexeme=yytext(); return opMultiplicacion;}
"/" {lexeme=yytext(); return opDivision;}

//definicion de comparadores
">" {lexeme=yytext(); return sigMayor;}
"<" {lexeme=yytext(); return sigMenor;}
">=" {lexeme=yytext(); return mayorIgual;}
"<=" {lexeme=yytext(); return menorIgual;}
"==" {lexeme=yytext(); return comparacion;}
"!=" {lexeme=yytext(); return diferente;}

//definicion de operadores incrementales
"++" {lexeme=yytext(); return incremento;}
"--" {lexeme=yytext(); return decremento;}
"=+" {lexeme=yytext(); return yoMas;}
"=-" {lexeme=yytext(); return yoMenos;}

//definicion de caracteres de agrupacion
"(" {lexeme=yytext(); return aParentesis;}
")" {lexeme=yytext(); return cParentesis;}
"{" {lexeme=yytext(); return aLlave;}
"}" {lexeme=yytext(); return cLlave;}
"[" {lexeme=yytext(); return aCorchete;}
"]" {lexeme=yytext(); return cCorchete;}

//definicion de operadores logicos
"or" {lexeme=yytext(); return opOr;}
"and" {lexeme=yytext(); return opAnd;}

//definicion del fin de cada linea
";" {lexeme=yytext(); return finLine;}


//definicion de reglas
[<]{Letras}({Letras}|{Digitos})*[>] {lexeme=yytext(); return Identificador;}
[/]({Letras}|{Digitos}|[ ])*[/] {lexeme=yytext(); return cadena;}
[-]?{Digitos}|{Digitos}+ {lexeme=yytext(); return Numero;}
[-]?{Digitos}*+[.]{Digitos}+ {lexeme=yytext(); return Decimal;}
(true|false){1} {lexeme=yytext(); return booleano;}
 . {lexeme=yytext(); return ERROR;}
