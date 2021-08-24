pub mod pep_243
{
    use reikna::prime::prime_sieve;
    use std::collections::BinaryHeap;
    use std::cmp::Reverse;

    pub fn solve() -> u64
    {
        let factors = prime_sieve(100);
        let mut heap = BinaryHeap::new();
        heap.push(Reverse((2, 0, 1)));

        loop {
            if let Some(Reverse((n, i, phi))) = heap.pop()
            {
                let f0 = factors[i];
                let f1 = factors[i + 1];
                heap.push(Reverse((f0 * n, i, f0 * phi)));
                heap.push(Reverse((f1 * n, i + 1, (f1 - 1) * phi)));
                if phi as f64 / (n as f64 - 1_f64) < 15499_f64 / 94744_f64 {
                    break n;
                }
            }
        }
    }
}
