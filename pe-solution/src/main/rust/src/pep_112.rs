fn is_bouncy(n: u64) -> bool {
    let char_vec: Vec<char> = n.to_string().chars().collect();

    let compared_numbers = char_vec.iter()
        .zip(char_vec[1..].iter())
        .filter(|(a, b)| a != b)
        .map(|(a, b)| a > b);

    compared_numbers.clone()
        .zip(compared_numbers.skip(1))
        .filter(|(a, b)| a != b)
        .collect::<Vec<_>>()
        .len() > 0
}

pub fn solve() -> u64 {
    assert!(!is_bouncy(134468), "134468 should be not bouncy");
    assert!(!is_bouncy(66420), "66420 should be not bouncy");
    assert!(is_bouncy(155349), "155349 should be bouncy");

    let mut count = 0;
    for a in (100..).into_iter()
        .map(|n| n as u64)
        .filter(|n| is_bouncy(*n)) {
        count += 1;
        if (count as f64 * (1_f64 / 0.99)) >= a as f64 { return a; }
    }

    panic!("This shouldn't happen")
}
