use serde::Deserialize;

#[derive(Debug, Deserialize)]
struct Triangle {
    a: Point2D,
    b: Point2D,
    c: Point2D,
}

#[derive(Debug, Deserialize)]
struct Point2D {
    x: i32,
    y: i32,
}

impl Triangle {
    fn contains_point(self: &Triangle, point: Point2D) -> bool {
        let segment_a_b = (point.x - self.b.x) * (self.a.y - self.b.y)
            - (self.a.x - self.b.x) * (point.y - self.b.y);
        let segment_b_c = (point.x - self.c.x) * (self.b.y - self.c.y)
            - (self.b.x - self.c.x) * (point.y - self.c.y);
        let segment_c_a = (point.x - self.a.x) * (self.c.y - self.a.y)
            - (self.c.x - self.a.x) * (point.y - self.a.y);

        ((segment_a_b < 0) && (segment_b_c < 0) && (segment_c_a < 0))
            || ((segment_a_b > 0) && (segment_b_c > 0) && (segment_c_a > 0))
    }

    fn contains_origin(self: &Triangle) -> bool {
        let origin = Point2D { x: 0, y: 0 };
        self.contains_point(origin)
    }
}

static INPUT_FILENAME: &str = "../resources/pep_102/p102_triangles.txt";

pub fn solve() -> u64 {
    csv::ReaderBuilder::new()
        .has_headers(false)
        .from_path(INPUT_FILENAME)
        .unwrap()
        .deserialize()
        .flatten()
        .map(|t: Triangle| if t.contains_origin() { 1 } else { 0 })
        .sum()
}
