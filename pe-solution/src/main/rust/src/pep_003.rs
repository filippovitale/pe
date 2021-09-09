pub fn solve() -> u64 {
    let input: u64 = 600851475143;

    let mut num = input;
    let mut divider = 3;
    let divider_step_up = 2;

    while divider < num {
        if num % divider == 0 {
            num /= divider;
        } else {
            divider += divider_step_up;
        }
    }

    num
}
