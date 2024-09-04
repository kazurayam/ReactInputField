# A local web server that responds a React-powered page, accompanied with E2E tests by Katalon Recorder, Katalon Studio and Playwright

@author: @kazurayam

@date: 4 Sept, 2024

## What is this?

[This GitHub repository](https://github.com/kazurayam/ReactInputField) contains 4 subprojects:

1. The [my-next-app](https://github.com/kazurayam/ReactInputField/tree/develop/my-next-app) subproject is a [Next.js](https://nextjs.org/docs) project that can launch a local web server with a React.js page at the URL of http://localhost:3000
2. The [e2e_KatalonRecorder](https://github.com/kazurayam/ReactInputField/tree/develop/e2e_KatalonRecorder) subproject contains a serialized test code for [Katalon Recorder](https://katalon.com/katalon-recorder-ide), that targets the URL lauched by the my-next-app project.
3. The [e2e_KatalonStudio](https://github.com/kazurayam/ReactInputField/tree/develop/e2e_KatalonStudio) subproject contains a [Katalon Studio](https://katalon.com/katalon-studio) project that targets the same local URL.
4. The [e2e_Playwright](https://github.com/kazurayam/ReactInputField/tree/develop/testby_playwright) subproject is a [Playwright](https://playwright.dev/) project that targets the same local URL.

Provided that you have setup your machine with [Node.js](https://nodejs.org/en) and other external resources appropriately, you can reproduce a problem discussed in the Katalon user forum at:

- [Serious Recorder Bug - does not work with react](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)

I would refer to this topic as "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)" for short.

## Problem to solve

In the "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/14308/7)", @guy.mason wrote

>I personally use Katalon Recorder v7 with a React application, and it works for the greater majority of scenarios, but there is a particular text input field that where the field should automatically trigger an event when the contents changes, but presently doesn’t.

I wanted to reproduce on my machine what's reported by @Rob1 and @guy.mason, and I found the reproduction was not very easy.

In the Katalon User Forum, we see a growing number of topics (questions and bug reports) about E2E testing for React apps. I find a common shortcoming in those topics. They talk about what they found in their own environment. They tend not to disclose any information for others how to reproduce the incident. Why they don't do that? It is because their React apps as AUT (*Application Under Test*) run inside their private network, is not published to the Internet, so that nobody outside their organization can get access to their AUT.

Without the problem UAT in action on my machine, I can never be sure what's the problem the issuers reported.

## Proposed Solution

With [Node.js](https://nodejs.org/en) and [Next.js](https://nextjs.org/docs) installed on my machine, I can easily launch a React app locally on my machine.

1. I have created a primitive Next.js project in the [my-next-app](https://github.com/kazurayam/ReactInputField/tree/develop/my-next-app). Anyone can check it out to their machine and quickly run it. Also I will explain how to create the same from scratch. It's really easy.

2. I can edit a TypeScript code [my-next-app/src/app/page.txt](https://github.com/kazurayam/ReactInputField/blob/develop/my-next-app/src/app/page.tsx) which will generate a HTML page in response to a request for http://localhost:3000. With this URL as AUT, I could create and ran E2E tests using any testing tools: Katalon Recorder, Katalon Studio and Playwright.

3. I will refer to the official React documentation ["LEARN REACT"](https://react.dev/learn) where I can find lots of sample React codes. I can chose one, copy&paste it into the [my-next-app/src/app/page.txt](https://github.com/kazurayam/ReactInputField/blob/develop/my-next-app/src/app/page.tsx) and bring it in action at http://locahost:3000.

3. For example, I refered to the source of App.tsx at https://react.dev/learn/typescript#typing-dom-events, titled as "DOM Events", which demonstrates an `<input onChange="...">`. This could be the appropriate AUT to reproduce the "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)" issue.

## Prerequisites

1. Here I assume that you, the reader of this article, are a well trained JavaScript programmer. You have [Node.js](https://nodejs.org/en) installed in action. You don't need me to explain the source codes in JavaScript/TypeScript.
2. I also assume that you don't need me to explain how to install Katalon products.
3. My environment is as follows:
  - OS: macOS 14.6.1
  - Terminal interpreter: bash
  - Node version: v21.6.2
  - Next.js verion: v14.2.7
  - Katalon Recorder version: on Chrome, v7.1.0
  - Katalon Studio version: v9.0.0 Free
  - Playwright version: ^1.46.1
3. You should be able reuse [this repository](https://github.com/kazurayam/ReactInputField) on Windows as well, though I haven't examined it.

## How to run the demonstration

```
$ pwd
/Users/kazurayam/tmp/ReactInputField-0.1.0

$ ls
README.md		e2e_KatalonRecorder	e2e_Playwright
docs			e2e_KatalonStudio
```

```
$ npx create-react-app my-next-app
✔ Would you like to use TypeScript? … No / Yes
✔ Would you like to use ESLint? … No / Yes
✔ Would you like to use Tailwind CSS? … No / Yes
✔ Would you like to use `src/` directory? … No / Yes
✔ Would you like to use App Router? (recommended) … No / Yes
✔ Would you like to customize the default import alias (@/*)? … No / Yes

```

The "npx create-react-app" command too a few minuites to finish.

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

I found a new directory `my-next-app` was created. The directory contains a lot of files and subdirectories, but you do not have to worry about the volume:

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

We are interested in only a single file: **`my-next-app/src/app/page.tsx`**

Please open the `my-next-app/src/app/page.tsx` file in your favorite text editor, and remove all the lines. Make it empty!

Then you want copy&page the []()




## How to make the subprojects from scratch
