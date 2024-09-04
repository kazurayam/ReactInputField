# A local web server that responds a React-powered page, accompanied with E2E tests by Katalon Recorder, Katalon Studio and Playwright

@author: @kazurayam

@date: 4 Sept, 2024

## What is this?

[This GitHub repository](https://github.com/kazurayam/ReactInputField) contains 4 subdirectories:

1. The [e2e_KatalonRecorder](https://github.com/kazurayam/ReactInputField/tree/develop/e2e_KatalonRecorder) subproject contains a serialized test code for [Katalon Recorder](https://katalon.com/katalon-recorder-ide), that targets the URL http://localhost:3000 .
2. The [e2e_KatalonStudio](https://github.com/kazurayam/ReactInputField/tree/develop/e2e_KatalonStudio) subproject contains a [Katalon Studio](https://katalon.com/katalon-studio) project that targets the same local URL.
3. The [NextAppSrc](https://github.com/kazurayam/ReactInputField/tree/develop/NextAppSrc) subdirectory contains a single TypeScript `page.tsx`. Later you will create a Next.js project, and then you will copy & paste this `page.tsx` into the newly created Next.js project.
4. The [PlaywrightTestSrc](https://github.com/kazurayam/ReactInputField/tree/develop/PlaywrightTestSrc) subproject contains a single TypeScript `page.spec.ts`. Later will will create a Playwright project, and then you will copy & page this `page.spec.ts` into the newly created Playwright project.

Provided that you have setup your machine with [Node.js](https://nodejs.org/en) and other external resources appropriately, you can reproduce a problem on your machine, which was discussed in the Katalon user forum at:

- [Serious Recorder Bug - does not work with react](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)

I would refer to this topic as "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)" for short.

## Problem to solve

In the "KR+React issue", @guy.mason wrote

>I personally use Katalon Recorder v7 with a React application, and it works for the greater majority of scenarios, but there is a particular text input field that where the field should automatically trigger an event when the contents changes, but presently doesn’t.

I wanted to reproduce on my machine what's reported by @Rob1 and @guy.mason, and I found it was not very easy.

I find a growing number of topics (questions and bug reports) about E2E testing for React apps in the Katalon User Forum. Now I would argue that there is a common shortcoming in those topics. The topic-posters talk about what they encountered in their own environment, and **they tend to disclose no guidance how to reproduce the incident in the environments of someone else**.

Why they don't do that? I suppse, because their React apps as AUT (*Application Under Test*) run inside their private network so that the posters can not tell others: "Please have a look at this URL on the Internet for this incident".

I would argue, however, I can never understand the problems unless I can see the problem is reproduced with a live URL accessble on my machine. Therefore I want to lauch a React-based AUT locally on my machine. How can I do it?

## Proposed Solution

With [Node.js](https://nodejs.org/en) installed, I can easily launch a React app locally on my machine.

1. I will create a Next.js project on my machine. Next.js lets us generate a new project by a single command on top of Node.js. It's really easy.

2. I will read the official React documentation ["LEARN REACT"](https://react.dev/learn) where I can find lots of sample React codes. I can choose one, copy & paste it into the `src/app/page.tsx` file in the generated Next.js project. I will bring the local web server in action at http://locahost:3000.

3. For example, I refered to the source of App.tsx at https://react.dev/learn/typescript#typing-dom-events, titled as "DOM Events", which demonstrates an `<input onChange="...">`. This could be an appropriate AUT to reproduce the "KR+React issue" issue.

4. Thus a React-power web page will be availabe at http://localhost:3000. With this URL as AUT, I can create a set of E2E tests using any testing tools. I have created a set of demonstrative codes of Katalon Recorder, Katalon Studio and Playwright.

## Prerequisites

1. Here I assume that you, the reader of this README page, are a well-trained JavaScript programmer. You have [Node.js](https://nodejs.org/en) installed on your machine. You don't need me to explain any of JavaScript/TypeScript source codes.
2. I assume that you don't need me to explain how to install Katalon Recorder and Katalon Studio.
3. My environment is as follows:
  - OS: macOS 14.6.1
  - Terminal interpreter: bash
  - Node version: v21.6.2
  - Next.js verion: v14.2.7
  - Katalon Recorder version: on Chrome, v7.1.0
  - Katalon Studio version: v9.0.0 Free
  - Playwright version: ^1.46.1

I think that you should be able apply the description here on Windows as well, though I haven't examined it.

## Aquiring the demonstration zip

Visit the Release page https://github.com/kazurayam/ReactInputField/releases

Find the zip file of the latest version; download it; unzip it into any directory.


## Launching a local web server powered by React.js

I created a new directory `/Users/kazurayam/tmp/ReactInputField-0.1.0`. This is just an example. You can chose wherever you like.

```
$ pwd
/Users/kazurayam/tmp/ReactInputField-0.1.0

$ ls
README.md		e2e_KatalonRecorder	e2e_Playwright
docs			e2e_KatalonStudio
```

Now I will create a new Next.js project named `my-next-app`. It is done by a single command.

>The `npx` command is bundled in Node.js. You MUST have Node.js installed on your machine.

```
$ npx create-react-app my-next-app

✔ Would you like to use TypeScript? … No / Yes
✔ Would you like to use ESLint? … No / Yes
✔ Would you like to use Tailwind CSS? … No / Yes
✔ Would you like to use `src/` directory? … No / Yes
✔ Would you like to use App Router? (recommended) … No / Yes
✔ Would you like to customize the default import alias (@/*)? … No / Yes
```

The "npx create-react-app" command takes a few minuites to finish.

```
Creating a new Next.js app in /Users/kazuakiurayama/tmp/ReactInputField-0.1.0/my-next-app.

Using npm.

Initializing project with template: app-tw


Installing dependencies:
- react
- react-dom
- next

Installing devDependencies:
- typescript
- @types/node
- @types/react
- @types/react-dom
- postcss
- tailwindcss
- eslint
- eslint-config-next

npm warn deprecated inflight@1.0.6: This module is not supported, and leaks memory. Do not use it. Check out lru-cache if you want a good and tested way to coalesce async requests by a key value, which is much more comprehensive and powerful.
npm warn deprecated @humanwhocodes/config-array@0.11.14: Use @eslint/config-array instead
npm warn deprecated rimraf@3.0.2: Rimraf versions prior to v4 are no longer supported
npm warn deprecated @humanwhocodes/object-schema@2.0.3: Use @eslint/object-schema instead
npm warn deprecated glob@7.2.3: Glob versions prior to v9 are no longer supported

added 363 packages, and audited 364 packages in 1m

136 packages are looking for funding
  run `npm fund` for details

found 0 vulnerabilities
Initialized a git repository.

Success! Created my-next-app at /Users/kazurayam/tmp/ReactInputField-0.1.0/my-next-app
```

I found a new directory `my-next-app` was created. The directory contains a lot of files and subdirectories, but we do not worry about them much:

```
$ pwd
/Users/kazurayam/tmp/ReactInputField-0.1.0/my-next-app
$ tree . -L 3
.
├── next-env.d.ts
├── next.config.mjs
├── node_modules
...
├── package-lock.json
├── package.json
├── postcss.config.mjs
├── public
...
├── src
│   └── app
│       ├── favicon.ico
│       ├── globals.css
│       ├── layout.tsx
│       └── page.tsx    <=== THIS IS IT!
├── tailwind.config.ts
└── tsconfig.json
```

We are interested in only a single file: **`my-next-app/src/app/page.tsx`**. The `page.tsx` will generate the HTML of the top page when we open http://localhost:3000 in a browser.

Please open the `my-next-app/src/app/page.tsx` file with your favorite text editor. *Remove all the lines. Make the `page.tsx` file empty!*

Then, you want to copy and paste the [code of page.txt](https://github.com/kazurayam/ReactInputField/blob/develop/NextAppSrc/src/app/page.tsx) that I created, which is as follows:

```
'use client';

// original: https://react.dev/learn/typescript#typing-dom-events

import { useState } from 'react';

export default function Form() {
  const [value, setValue] = useState('Change me');

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    setValue(event.currentTarget.value);
  }

  return (
    <>
      <input value={value} onChange={handleChange}/>
      <p>Value: {value}</p>
    </>
  );
}
```

Now we are ready to start the local web server. Run the following command:

```
$ pwd
/Users/kazurayam/tmp/ReactInputField-0.1.0/my-next-app

$ npm run dev
```

The server will start up in a few seconds:

```
$ npm run dev

> my-next-app@0.1.0 dev
> next dev

  ▲ Next.js 14.2.7
  - Local:        http://localhost:3000

 ✓ Starting...
 ✓ Ready in 5.4s
```

Now you can visit http://localhost:3000. You will see a page like this:

![my-next-app](https://kazurayam.github.io/ReactInputField/images/my-next-app.png)

This is what I wanted to see on my machine.

This page is a mimic of a sample in the official React documentation: https://react.dev/learn/typescript#typing-dom-events

As you can easily see, the 2 pages look different, but both has the same DOM structure and JavaScript event handlers. Both have an `<input>` element and a `<p>` element rendered by React.js. The `<input>` has `onChange` event-handler defined, which should update the `<p>` content as soon as you type a text into the `<input>`. With this URL in action, I would be able to reproduce the problem discussed at the [KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083).

## Reproducing the problem of Katalon Recorder

I have developed a test script for Katalon Recorder:

- [KRtest](https://github.com/kazurayam/ReactInputField/blob/develop/e2e_KatalonRecorder/TestLocalReactInputField.krecorder)

When I ran this test, the test failed.

![KR issue](https://kazurayam.github.io/ReactInputField/images/KatalonRecorderIssue.png)

Why? Katalon Recorder sent a text "Hooah" into the `<input onChange="...">` element, which seems to be accepted. I would expect the same text is trasfered into the sibling `<p>` element. But, apparently the `onChange` event handler was not fired. I have no idea why.

So, I agree with the original poster of the the [KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083). Katalon Recorder has a problem.

## Testing the same AUT using Katalon Studio.

I made a Test Case for Katalon Studio.

- [Test Cases/Scripts/TC1](https://github.com/kazurayam/ReactInputField/blob/develop/e2e_KatalonStudio/Scripts/TC1/Script1724447592246.groovy)

This test runs fine. No issue. Katalon Studio works differently from Katalon Recorder.

## Testing the same AUT using Playwright.

Here I would introduce you to another E2E testing tool [Playwright](https://playwright.dev/).

You want to create a directory where you locate a new Playwright project.

```
$ pwd
~/tmp/ReactInputField-0.1.0/
$ mkdir e2e_Playwright
```

You want to cd into the directory and create a new Playwright testing project

```
$ cd e2e_Playwright
$ npm init playwright@latest
```

It will take a few minutes:

```
$ npm init playwright@latest

> npx
> create-playwright

Getting started with writing end-to-end tests with Playwright:
Initializing project in '.'
✔ Do you want to use TypeScript or JavaScript? · TypeScript
✔ Where to put your end-to-end tests? · tests
✔ Add a GitHub Actions workflow? (y/N) · false
✔ Install Playwright browsers (can be done manually via 'npx playwright install')? (Y/n) · true

Initializing NPM project (npm init -y)…
Wrote to /Users/kazurayam/tmp/ReactInputField-0.1.0/e2e_Playwright/package.json:

{
  "name": "e2e_playwright",
  "version": "1.0.0",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "description": ""
}


Installing Playwright Test (npm install --save-dev @playwright/test)…

added 3 packages, and audited 4 packages in 9s

found 0 vulnerabilities
Installing Types (npm install --save-dev @types/node)…

added 3 packages, and audited 7 packages in 2s

found 0 vulnerabilities
Writing playwright.config.ts.
Writing tests/example.spec.ts.
Writing tests-examples/demo-todo-app.spec.ts.
Writing package.json.
Downloading browsers (npx playwright install)…
✔ Success! Created a Playwright Test project at /Users/kazurayam/tmp/ReactInputField-0.1.0/e2e_Playwright

Inside that directory, you can run several commands:

  npx playwright test
    Runs the end-to-end tests.

  npx playwright test --ui
    Starts the interactive UI mode.

  npx playwright test --project=chromium
    Runs the tests only on Desktop Chrome.

  npx playwright test example
    Runs the tests in a specific file.

  npx playwright test --debug
    Runs the tests in debug mode.

  npx playwright codegen
    Auto generate tests with Codegen.

We suggest that you begin by typing:

    npx playwright test

And check out the following files:
  - ./tests/example.spec.ts - Example end-to-end test
  - ./tests-examples/demo-todo-app.spec.ts - Demo Todo App end-to-end tests
  - ./playwright.config.ts - Playwright Test configuration

Visit https://playwright.dev/docs/intro for more information. ✨

Happy hacking! 🎭
```

In the `e2e_Playwright` directory, there are lots of files and directories automatically layed out, but you do not need to worry about them.

You want to add a new file `e2e_Playwright/tests/page.specs.ts`. You can copy and paste [this code](https://github.com/kazurayam/ReactInputField/blob/develop/PlaywrightTestSrc/tests/page.spec.ts) that I created. The content is as follows:

```
import test, { expect } from "playwright/test";

test("<input> field in a React app work as expected", async({ page }) => {
  // visit the target URL
  await page.goto("http://localhost:3000");
  await expect(page.locator("p")).toContainText("Value: Change me");
  await page.locator("input").fill("Hooah");
  await expect(page.locator("p")).toContainText("Value: Hooah");
  await page.close();
});
```

I would evaluate this test code is the best readable and concise amonst all E2E testing tools that I know. The `await` keyword looks beautiful. I think that the `await` keyword is indispensable to test any modern JavaScirpt web apps.

Now you are ready to run the test by Playwright.

Just make sure that the local server is up and running at http://localhost:3000

Then you want to  run a command:

```
$ pwd
~/tmp/ReactInputField/e2e_Playwright

$ npx playwright test
```

This command executes all the tests in the `e2e_Playwright/tests/` directory. I got the following result.

```
$ npx playwright test


Running 9 tests using 2 workers
[3/9] …omium] › page.spec.ts:3:5 › <input> field in a React app work as expecte[6/9] …refox] › page.spec.ts:3:5 › <input> field in a React app work as expecte[9/9] …ebkit] › page.spec.ts:3:5 › <input> field in a React app work as expecte  1) [webkit] › page.spec.ts:3:5 › <input> field in a React app work as expected

    Error: Timed out 5000ms waiting for expect(locator).toContainText(expected)

    Locator: locator('p')
    Expected string: "Value: Hooah"
    Received string: "Value: Change me"
    Call log:
      - expect.toContainText with timeout 5000ms
      - waiting for locator('p')
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"
      -   locator resolved to <p>…</p>
      -   unexpected value "Value: Change me"


       6 |   await expect(page.locator("p")).toContainText("Value: Change me");
       7 |   await page.locator("input").fill("Hooah");
    >  8 |   await expect(page.locator("p")).toContainText("Value: Hooah");
         |                                   ^
       9 |   await page.close();
      10 | });
      11 |

        at /Users/kazuakiurayama/tmp/ReactInputField-0.1.0/e2e_Playwright/tests/page.spec.ts:8:35

  1 failed
    [webkit] › page.spec.ts:3:5 › <input> field in a React app work as expected
  8 passed (28.4s)

  Serving HTML report at http://localhost:9323. Press Ctrl+C to quit.
```

I ran a command `npx playwright show-report`, which opend a new browser window. In their I saw the following test report.

![PlaywrightTestReport](https://kazurayam.github.io/ReactInputField/images/PlaywrightTestReport.png)

Playwright worked very quickly. It reported something interesting, which I am not going to talk about as is off topic.

## Concolusion

1. I could build a local server that serves an URL http://localhost:3000, which can potentially host any React-powered pages. Next.js makes every setup jobs easy.

2. Using this local server, I could reproduce the problem of Katalon Recorder as discussed at "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)". I found Katalon Recorder has a bug that should be addressed by Katalon.

3. I enjoyed developing a test for React app using Playwright. Through this experiment, I was impressed that Playwright is a good tool to test modern JavaScript web apps.
