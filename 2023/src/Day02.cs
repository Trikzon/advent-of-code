namespace Aoc2023;

public class Day02 : Day
{
    public Day02() : base("02") {}

    public override void Part1()
    {
        var games = Parse();
        
        var sum = 0;
        for (var i = 0; i < games.Count; i++)
        {
            var game = games[i];

            var impossible = false;
            foreach (var set in game)
            {
                if (set.TryGetValue("red", out var value) && value > 12)
                {
                    impossible = true;
                    break;
                }
                
                if (set.TryGetValue("green", out value) && value > 13)
                {
                    impossible = true;
                    break;
                }
                
                if (set.TryGetValue("blue", out value) && value > 14)
                {
                    impossible = true;
                    break;
                }
            }

            if (impossible)
            {
                continue;
            }

            sum += (i + 1);
        }
        
        Console.WriteLine($"Part 1: {sum}");
    }

    public override void Part2()
    {
        var games = Parse();

        var sum = 0;
        foreach (var game in games)
        {
            var minRed = 0;
            var minGreen = 0;
            var minBlue = 0;

            foreach (var set in game)
            {
                if (set.TryGetValue("red", out var value))
                {
                    if (value > minRed)
                    {
                        minRed = value;
                    }
                }
                
                if (set.TryGetValue("green", out value))
                {
                    if (value > minGreen)
                    {
                        minGreen = value;
                    }
                }
                
                if (set.TryGetValue("blue", out value))
                {
                    if (value > minBlue)
                    {
                        minBlue = value;
                    }
                }
            }
            sum += (minRed * minGreen * minBlue);
        }

        Console.WriteLine($"Part 2: {sum}");
    }

    private List<List<Dictionary<string, int>>> Parse()
    {
        var games = new List<List<Dictionary<string, int>>>();
        foreach (var line in Input.Split("\n"))
        {
            var game = new List<Dictionary<string, int>>();
            foreach (var setStr in line.Split(":")[1].Split(";"))
            {
                var set = new Dictionary<string, int>();
                foreach (var pull in setStr.Split(","))
                {
                    var count = int.Parse(pull.Trim().Split(" ")[0]);
                    var color = pull.Trim().Split(" ")[1].Trim();
                    
                    set.Add(color, count);
                }
                game.Add(set);
            }
            games.Add(game);
        }
        return games;
    }
}
