import test, { expect } from "playwright/test";

test("<input> field in a React app work as expected", async({ page }) => {
  // visit the target URL
  await page.goto("http://localhost:3000");
  await expect(page.locator("p")).toContainText("Value: Change me");
  await page.locator("input").fill("Hooah");
  await expect(page.locator("p")).toContainText("Value: Hooah");
  await page.close();
});
