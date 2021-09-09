// https://doc.rust-lang.org/rust-by-example/trait/iter.html
struct Fibonacci {
    curr: u64,
    next: u64,
}

impl Iterator for Fibonacci {
    type Item = u64;

    fn next(&mut self) -> Option<Self::Item> {
        let new_next = self.curr + self.next;
        self.curr = self.next;
        self.next = new_next;

        Some(self.curr)
    }
}

pub fn solve() -> u64 {
    let fibonacci_sequence = Fibonacci { curr: 0, next: 1 };
    fibonacci_sequence
        .skip(1)
        .take_while(|n| n < &4_000_000)
        .filter(|n| n % 2 == 0)
        .sum()
}
