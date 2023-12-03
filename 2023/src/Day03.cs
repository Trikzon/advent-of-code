namespace Aoc2023;

public class Day03 : Day
{
    private class PartNumber
    {
        public readonly int Value;
        private readonly int _startIndex;
        private readonly int _endIndex;
        private readonly int _gearIndex;

        public PartNumber(int value, int startIndex, int endIndex, int gearIndex)
        {
            Value = value;
            _startIndex = startIndex;
            _endIndex = endIndex;
            _gearIndex = gearIndex;
        }

        public bool IsGear(int lineWidth, PartNumber other)
        {
            var coords = new[]
            {
                _gearIndex - 1 - lineWidth, _gearIndex - lineWidth, _gearIndex + 1 - lineWidth,
                _gearIndex - 1, _gearIndex + 1,
                _gearIndex - 1 + lineWidth, _gearIndex + lineWidth, _gearIndex + 1 + lineWidth,
            };

            var isGear = false;
            foreach (var coord in coords)
            {
                if (coord >= other._startIndex && coord <= other._endIndex)
                    isGear = true;
            }
            
            return isGear;
        }
    }
    
    public Day03() : base("03") {}

    public override void Part1()
    {
        var partNumbers = Parse(false);

        var sum = partNumbers.Sum(partNumber => partNumber.Value);

        Console.WriteLine($"Part 1: {sum}");
    }

    public override void Part2()
    {
        var partNumbers = Parse(true);

        var lineWidth = Input.Split("\n")[0].Length;

        var sum = 0;
        for (var i = 0; i < partNumbers.Count; i++)
        {
            var part = partNumbers[i];
            var partCount = 1;
            PartNumber? other = null;
            
            for (var j = i + 1; j < partNumbers.Count; j++)
            {
                if (!part.IsGear(lineWidth, partNumbers[j])) continue;
                
                partCount += 1;
                other = partNumbers[j];
            }

            if (partCount == 2 && other != null)
            {
                sum += part.Value * other.Value;
            }
        }
        
        Console.WriteLine($"Part 2: {sum}");
    }

    private List<PartNumber> Parse(bool findGears)
    {
        var lineWidth = Input.Split("\n")[0].Length;
        var input = Input.Replace("\n", "");

        var partNumbers = new List<PartNumber>();

        var numStartIndex = -1;
        for (var i = 0; i < input.Length; i++)
        {
            // Continue if not a digit.
            if (input[i] is < '0' or > '9')
            {
                numStartIndex = -1;
                continue;
            }

            // If we find a digit, set the start index if it's the first one.
            if (numStartIndex == -1)
                numStartIndex = i;
            
            // Calculate the neighboring coords for simplifying our checks later.
            var coords = new[]
            {
                i - 1 - lineWidth, i - lineWidth, i + 1 - lineWidth,
                i - 1, i + 1,
                i - 1 + lineWidth, i + lineWidth, i + 1 + lineWidth,
            };

            // Check if the number is neighboring a valid symbol.
            var foundPartNum = false;
            var gearIndex = -1;
            foreach (var coord in coords)
            {
                if (findGears)
                {
                    if (coord >= input.Length || coord < 0 || input[coord] != '*') continue;
                    
                    foundPartNum = true;
                    gearIndex = coord;
                    
                    break;
                }

                if (coord >= input.Length || coord < 0 || input[coord] is >= '0' and <= '9' or '.') continue;
                
                foundPartNum = true;
                break;
            }
            
            // If we didn't find a part number, go again.
            if (!foundPartNum)
                continue;

            // Find where the number ends.
            var numEndIndex = 0;
            for (var j = numStartIndex; j < input.Length; j++)
            {
                if (input[j] is < '0' or > '9')
                    break;

                numEndIndex = j;
            }
            
            var numStr = input.Substring(numStartIndex, numEndIndex - numStartIndex + 1);
            
            // Replace the digits with '.' so that it doesn't get found again.
            for (var j = numStartIndex; j <= numEndIndex; j++)
            {
                input = input.Remove(j, 1).Insert(j, ".");
            }
            
            partNumbers.Add(new PartNumber(int.Parse(numStr), numStartIndex, numEndIndex, gearIndex));
            numStartIndex = -1;
        }

        return partNumbers;
    }
}
