use itertools::iproduct;

pub fn solve() -> u64 {
    iproduct!(1..1000, 1..1000)
        .filter(|(a, b)| a >= b)
        .map(|(a, b)| a * b)
        .filter(|a| a.to_string().chars().rev().collect::<String>() == a.to_string())
        .max()
        .unwrap_or_default()
}
