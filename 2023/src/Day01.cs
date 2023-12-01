namespace Aoc2023;

public class Day01 : Day
{
    public Day01() : base("01") {}
    
    public override void Part1()
    {
        var sum = 0;
        foreach (var line in Input.Split("\n"))
        {
            var firstDigit = 0;
            var lastDigit = 0;

            foreach (var c in line.ToCharArray())
            {
                if (c is < '0' or > '9')
                    continue;

                var digit = c - '0';
                if (firstDigit == 0)
                {
                    firstDigit = digit;
                    lastDigit = digit;
                }
                else
                {
                    lastDigit = digit;
                }
            }
            
            sum += (firstDigit * 10) + lastDigit;
        }
        
        Console.WriteLine($"Part 1: {sum}");
    }

    public override void Part2()
    {
        var digits = new Dictionary<string, int>
        {
            { "one", 1 },
            { "two", 2 },
            { "three", 3 },
            { "four", 4 },
            { "five", 5 },
            { "six", 6 },
            { "seven", 7 },
            { "eight", 8 },
            { "nine", 9 }
        };
        
        var sum = 0;
        foreach (var line in Input.Split("\n"))
        {
            var firstDigit = 0;
            var lastDigit = 0;
            
            for (var i = 0; i < line.Length; i++)
            {
                var digit = 0;
                
                if (line[i] is >= '0' and <= '9')
                {
                    digit = line[i] - '0';
                }
                else
                {
                    for (var j = 3; j <= 5; j++)
                    {
                        if (line.Length - i < j || !digits.ContainsKey(line.Substring(i, j))) continue;
                        
                        digit = digits[line.Substring(i, j)];
                        break;
                    }
                }

                if (digit <= 0) continue;
                
                if (firstDigit == 0)
                {
                    firstDigit = digit;
                    lastDigit = digit;
                }
                else
                {
                    lastDigit = digit;
                }
            }
            
            sum += (firstDigit * 10) + lastDigit;
        }

        Console.WriteLine($"Part 2: {sum}");
    }
}
