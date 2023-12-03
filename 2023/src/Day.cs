namespace Aoc2023;

public abstract class Day
{
    protected string Input;
    
    protected Day(string day)
    {
        Input = File.ReadAllText($"../../../inputs/day{day}.in");
    }

    public abstract void Part1();

    public abstract void Part2();
}