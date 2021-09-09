fn accumulate_dividends(acc: u64, n: u64) -> u64 {
    acc * match acc % n {
        0 => 1,
        m =>
            match n % m {
                0 => n / m,
                _ => n
            }
    }
}

pub fn solve() -> u64 {
    (1..21).reduce(accumulate_dividends).unwrap_or_default()
}
