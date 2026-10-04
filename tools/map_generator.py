import math
import random

class PerlinNoise:
    def __init__(self, seed=None):
        if seed is not None:
            random.seed(seed)
        self.p = list(range(256))
        random.shuffle(self.p)
        self.p += self.p

    def _fade(self, t):
        return t * t * t * (t * (t * 6 - 15) + 10)

    def _lerp(self, t, a, b):
        return a + t * (b - a)

    def _grad(self, hash_val, x, y):
        h = hash_val & 3
        u = x if h < 2 else y
        v = y if h < 2 else x
        return (u if (h & 1) == 0 else -u) + (v if (h & 2) == 0 else -v)

    def noise(self, x, y):
        X = int(math.floor(x)) & 255
        Y = int(math.floor(y)) & 255

        x -= math.floor(x)
        y -= math.floor(y)

        u = self._fade(x)
        v = self._fade(y)

        A = self.p[X] + Y
        B = self.p[X + 1] + Y

        return self._lerp(v, self._lerp(u, self._grad(self.p[A], x, y),
                                         self._grad(self.p[B], x - 1, y)),
                             self._lerp(u, self._grad(self.p[A + 1], x, y - 1),
                                         self._grad(self.p[B + 1], x - 1, y - 1)))

def generate_realistic_map(width, height, filename="realistic.map"):
    print(f"Generating a realistic {width}x{height} world...")

    perlin = PerlinNoise(seed=random.randint(0, 99999))
    map_data = []

    # Scale controls how zoomed in/out the map features are
    scale = 40.0

    for y in range(height):
        row = []
        for x in range(width):
            # Multi-octave Fractal Noise for realistic mountains & terrain blending
            nx = x / scale
            ny = y / scale

            val = 0.5 * perlin.noise(nx, ny) + \
                  0.25 * perlin.noise(nx * 2, ny * 2) + \
                  0.125 * perlin.noise(nx * 4, ny * 4)

            # Normalize to 0.0 - 1.0 range safely
            val = (val + 0.8) / 1.6
            val = max(0.0, min(1.0, val))

            # Tile mapping based on realistic elevation/humidity simulation:
            # 0: Grass, 1: Wall (Mountains), 2: Water, 3: Earth, 4: Tree, 5: Sand
            if val < 0.28:
                tile = 2  # Deep Water / Oceans
            elif val < 0.33:
                tile = 5  # Sand (Beaches/Shores)
            elif val < 0.52:
                tile = 0  # Grass (Plains and Fields)
            elif val < 0.70:
                tile = 4  # Tree (Dense Forests)
            elif val < 0.84:
                tile = 3  # Earth (Hills and Dirt trails)
            else:
                tile = 1  # Wall (High rocky mountains)

            row.append(str(tile))
        map_data.append(" ".join(row))

    with open(filename, "w") as f:
        f.write("\n".join(map_data))

    print(f"Realistic map successfully saved to {filename}!")

if __name__ == "__main__":
    # Choose any dimension you want here! (e.g., 100x100, 200x200, etc.)
    WIDTH = 1000
    HEIGHT = 1000
    generate_realistic_map(WIDTH, HEIGHT, "res/maps/world.map")