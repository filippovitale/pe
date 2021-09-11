use num::integer::Roots;

fn is_concealed_square(n: u64) -> bool {
    let s = n.to_string();
    let a = s.as_bytes();

    a[16] == (9 + 48) &&
        a[14] == (8 + 48) &&
        a[12] == (7 + 48) &&
        a[10] == (6 + 48) &&
        a[8] == (5 + 48) &&
        a[6] == (4 + 48) &&
        a[4] == (3 + 48) &&
        a[2] == (2 + 48)
}

pub fn solve() -> u64 {
    let min_square: u64 = 1020304050607080900;
    let max_square: u64 = 1929394959697989990;

    let min: u64 = min_square.sqrt();
    println!("min: {:?}", min);

    let max: u64 = max_square.sqrt();
    println!("max: {:?}", max);

    (0..).map(|i| (((max / 10) - i) * 10))
        .map(|n| (n, (n * n)))
        .skip_while(|(_i, n)| !is_concealed_square(*n))
        .take(1)
        .next()
        .unwrap_or_default().0
}
