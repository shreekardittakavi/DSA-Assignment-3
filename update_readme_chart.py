import csv
import math
from pathlib import Path


CSV_PATH = Path("results.csv")
README_PATH = Path("README.md")
START_MARKER = "<!-- CHART:START -->"
END_MARKER = "<!-- CHART:END -->"
DEFAULT_SIZES = [50, 500, 1000, 2000, 5000, 10000]


def read_results(path: Path) -> tuple[list[str], list[list[float]]]:
    with path.open(newline="", encoding="utf-8") as f:
        rows = list(csv.reader(f))

    if not rows:
        raise ValueError("results.csv is empty.")

    headers = [h.strip() for h in rows[0] if h.strip()]
    data_rows: list[list[float]] = []

    for raw in rows[1:]:
        values = [v.strip() for v in raw if v.strip()]
        if not values:
            continue
        if len(values) != len(headers):
            raise ValueError(
                f"Row has {len(values)} values but expected {len(headers)}: {values}"
            )
        data_rows.append([float(v) for v in values])

    if not data_rows:
        raise ValueError("results.csv has no data rows.")

    return headers, data_rows


def build_mermaid(headers: list[str], rows: list[list[float]]) -> str:
    sizes = DEFAULT_SIZES[: len(rows)]
    if len(sizes) < len(rows):
        sizes = list(range(1, len(rows) + 1))

    max_value = max(max(row) for row in rows)
    y_max = max(1, int(math.ceil(max_value * 1.05)))

    lines = [
        "```mermaid",
        "xychart-beta",
        '    title "Sorting Runtime vs Input Size"',
        f'    x-axis "Input Size" [{", ".join(str(s) for s in sizes)}]',
        f'    y-axis "Time (ms)" 0 --> {y_max}',
    ]

    for idx, name in enumerate(headers):
        series = ", ".join(f"{row[idx]:.4f}" for row in rows)
        lines.append(f'    line "{name}" [{series}]')

    lines.append("```")
    return "\n".join(lines)


def update_readme(readme_path: Path, chart_block: str) -> None:
    content = readme_path.read_text(encoding="utf-8")

    start = content.find(START_MARKER)
    end = content.find(END_MARKER)
    if start == -1 or end == -1 or end < start:
        raise ValueError("Could not find chart markers in README.md.")

    start_insert = start + len(START_MARKER)
    new_content = (
        content[:start_insert]
        + "\n"
        + chart_block
        + "\n"
        + content[end:]
    )
    readme_path.write_text(new_content, encoding="utf-8")


def main() -> None:
    headers, rows = read_results(CSV_PATH)
    chart_block = build_mermaid(headers, rows)
    update_readme(README_PATH, chart_block)
    print("README chart updated from results.csv")


if __name__ == "__main__":
    main()
