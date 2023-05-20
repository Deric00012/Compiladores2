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
wdh_int {lexeme=yytext(); return palabraReservadaInt;}
wdh_double {lexeme=yytext(); return palabraReservadaDouble;}
wdh_float {lexeme=yytext(); return palabraReservadaFloat;}
wdh_long {lexeme=yytext(); return palabraReservadaLong;}
wdh_bool {lexeme=yytext(); return palabraReservadaBool;}
wdh_string {lexeme=yytext(); return palabraReservadaString;}
wdh_funtion {lexeme=yytext(); return palabraReservadaFuntion;}
wdh_return {lexeme=yytext(); return palabraReservadaReturn;}
wdh_if {lexeme=yytext(); return palabraReservadaIf;}
wdh_else {lexeme=yytext(); return palabraReservadaElse;}
wdh_array {lexeme=yytext(); return palabraReservadaArray;}
wdh_while {lexeme=yytext(); return palabraReservadaWhile;}
wdh_do {lexeme=yytext(); return palabraReservadaDo;}
wdh_switch {lexeme=yytext(); return palabraReservadaSwitch;}
wdh_case {lexeme=yytext(); return palabraReservadaCase;}
wdh_default {lexeme=yytext(); return palabraReservadaCase;}
wdh_break {lexeme=yytext(); return palabraReservadaBreak;}
wdh_for {lexeme=yytext(); return palabraReservadaFor;}
wdh_class {lexeme=yytext(); return palabraReservadaClass;}
wdh_const {lexeme=yytext(); return palabraReservadaConst;}
wdh_void {lexeme=yytext(); return palabraReservadaVoid;}
wdh_exit {lexeme=yytext(); return palabraReservadaExit;}
wdh_new {lexeme=yytext(); return palabraReservadaNew;}
wdh_struct {lexeme=yytext(); return palabraReservadaStruct;}
wdh_include {lexeme=yytext(); return palabraReservadaInclude;}
wdh_public {lexeme=yytext(); return palabraReservadaPublic;}
wdh_private {lexeme=yytext(); return palabraReservadaPrivate;}


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
[<]{Digitos}({Letras}|{Digitos})*[>] {lexeme=yytext(); return errorIdentificador;}
[/]({Letras}|{Digitos}|[ ])*[/] {lexeme=yytext(); return cadena;}
[-]?{Digitos}|{Digitos}+ {lexeme=yytext(); return Numero;}
[-]?{Digitos}*+[.]{Digitos}+ {lexeme=yytext(); return Decimal;}
(true|false){1} {lexeme=yytext(); return booleano;}
({Letras}|{Digitos})* {lexeme=yytext(); return errorEnLinea;}
 . {lexeme=yytext(); return ERROR;}
