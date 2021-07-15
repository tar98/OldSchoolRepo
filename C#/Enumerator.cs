using System;

namespace Enum_Test
{
    enum std_enum //convertibile in qualsiasi intero (long, int, short)
    {
        a = 1, // qualsiasi
        b = -1 // unsigned non valido
    };

    enum int_enum : int  //convertibile 'solo' in int
    {
        a = -32768, //short + 1
        b = -2147483647 // int - 1
    }

    enum ushort_enum : ushort //convertibile 'solo' in ushort
    {
        a = 3,
        b = 65535 //Max ushort
    }

    class Enumerator
    {
        static void Main(string[] args)
        {
            std_enum std_Enum = std_enum.a;
            int_enum int_Enum = int_enum.b;
            ushort_enum ushort_Enum = ushort_enum.b;

            Console.WriteLine(std_Enum + " = " + Convert.ToInt16(std_Enum)); // Short
            Console.WriteLine(std_Enum + " = " + Convert.ToUInt32(std_Enum)); // UInt
            Console.WriteLine(std_Enum + " = " + Convert.ToInt64(std_Enum)); // Long

            //Console.WriteLine(int_Enum + " = " + Convert.ToInt16(int_Enum)); // Short Error
            Console.WriteLine(int_Enum + " = " + Convert.ToInt32(int_Enum)); // Int
            //Console.WriteLine(int_Enum + " = " + Convert.ToUInt64(int_Enum)); // ULong Error

            //Console.WriteLine(ushort_Enum + " = " + Convert.ToInt16(ushort_Enum)); // Short Error
            Console.WriteLine(ushort_Enum + " = " + Convert.ToUInt16(ushort_Enum)); // UShort Correct
            Console.WriteLine(ushort_Enum + " = " + Convert.ToInt32(ushort_Enum)); // Int
            Console.WriteLine(ushort_Enum + " = " + Convert.ToInt64(ushort_Enum)); // Long
        }
    }
}
