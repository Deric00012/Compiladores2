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
//definicion de palabras reservadas, clases y caracteres a excluir
entero | si | contrario | mientras {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"INICIO" {/*Ignore*/}
"=" {lexeme=yytext(); return opIgual;}
"+" {lexeme=yytext(); return opSuma;}
"-" {lexeme=yytext(); return opResta;}
"*" {lexeme=yytext(); return opMultiplicacion;}
"/" {lexeme=yytext(); return opDivision;}
">" {lexeme=yytext(); return mayor;}
[<]{Letras}({Letras}|{Digitos})*[>] {lexeme=yytext(); return Identificador;}
("(-"{Digitos}+")")|{Digitos}+ {lexeme=yytext(); return Numero;}
 . {lexeme=yytext(); return ERROR;}
