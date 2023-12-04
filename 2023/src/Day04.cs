namespace Aoc2023;

public class Day04 : Day
{
    public Day04() : base("04") {}

    public override void Part1()
    {
        var totalPoints = ParseWinningNumberCounts()
            .Where(winningNumberCount => winningNumberCount != 0)
            .Sum(winningNumberCount => (int)Math.Pow(2, winningNumberCount - 1));

        Console.WriteLine($"Part 1: {totalPoints}");
    }

    public override void Part2()
    {
        var winningNumberCounts = ParseWinningNumberCounts();

        var cardCounts = new List<int>();
        for (var i = 0; i < winningNumberCounts.Count; i++)
        {
            cardCounts.Add(0);
        }

        var totalCards = 0;
        for (var i = 0; i < winningNumberCounts.Count; i++)
        {
            for (var j = i + 1; j < i + winningNumberCounts[i] + 1 && j < winningNumberCounts.Count; j++)
            {
                cardCounts[j] += 1;
            }

            totalCards++;

            if (cardCounts[i] <= 0) continue;
            
            cardCounts[i]--;
            i--;
        }
        
        Console.WriteLine($"Part 2: {totalCards}");
    }

    private List<int> ParseWinningNumberCounts()
    {
        var winningNumberCounts = new List<int>();

        foreach (var line in Input.Split("\n"))
        {
            var numbers = line.Split(":")[1].Trim();

            var points = 0;

            var winningNumbers = new List<int>();
            foreach (var winningNumberStr in numbers.Split("|")[0].Trim().Split(" "))
            {
                if (int.TryParse(winningNumberStr, out var winningNumber))
                {
                    winningNumbers.Add(winningNumber);
                }
            }

            foreach (var numberStr in numbers.Split("|")[1].Trim().Split(" "))
            {
                if (!int.TryParse(numberStr, out var number) || !winningNumbers.Contains(number)) continue;

                points++;
            }

            winningNumberCounts.Add(points);
        }

        return winningNumberCounts;
    }
}