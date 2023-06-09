/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package codigo;

/**
 *
 * @author ioch
 */
//se enumeran TODOS los tokens que seran utilizados
public enum Tokens {
    Reservadas,
    opIgual, //=
    opSuma, //+
    opResta, //-
    opMultiplicacion, //*
    opDivision, // /
    Identificador, // <algo>
    Numero, //123
    Decimal, //123.456
    ERROR, // .
    INICIO, //
    sigMayor, //>
    sigMenor, //<
    mayorIgual, //>=
    menorIgual, //<=
    comparacion, //==
    diferente, //!=
    incremento, //++
    decremento, //--
    yoMas, //=+
    yoMenos, //=-
    cadena, // /algo/
    finLine,// ;
    aParentesis, // (
    cParentesis, // )
    aLlave, // {
    cLlave, //}
    aCorchete, //[
    cCorchete, //]
    booleano,
    opOr,
    opAnd,//verdader|falso
    errorIdentificador,
    errorEnLinea,
    palabraReservadaExit,
    palabraReservadaFuntion,
    palabraReservadaBool,
    palabraReservadaLong,
    palabraReservadaDo,
    palabraReservadaClass,
    palabraReservadaFloat,
    palabraReservadaWhile,
    palabraReservadaSwitch,
    palabraReservadaBreak,
    palabraReservadaConst,
    palabraReservadaDouble,
    palabraReservadaIf,
    palabraReservadaArray,
    palabraReservadaReturn,
    palabraReservadaInt,
    palabraReservadaPrivate,
    palabraReservadaStruct,
    palabraReservadaString,
    palabraReservadaPublic,
    palabraReservadaNew,
    palabraReservadaElse,
    palabraReservadaCase,
    palabraReservadaInclude,
    palabraReservadaVoid,
    palabraReservadaFor,
}
