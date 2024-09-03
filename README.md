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

In the Katalon User Forum, we see a growing number of topics (questions and bug reports) about E2E testing for React apps. I find a common shortcoming in those topics. They talk about what they found in their own envionment. They never disclose any information for others how to reproduce the incident on their own environment. The fundamental difficulty is that their React apps as AUT (*Application Under Test*) run on their private network, not public to the Internet, so that nobody else can get access to the AUT. Without the UAT in action on our own PC in hands, I can never be sure what's the problem the issuers reported.

## Proposed Solution

With [Node.js](https://nodejs.org/en) and [Next.js](https://nextjs.org/docs) installed on my machine, I can easily launch a React app locally on my machine.

1. I can edit a TypeScript code [my-next-app/src/app/page.txt](https://github.com/kazurayam/ReactInputField/blob/develop/my-next-app/src/app/page.tsx) which will generate a HTML page in response to a request for http://localhost:3000

2. I will refer to the official React documentation ["LEARN REACT"](https://react.dev/learn) where I can find lots of sample React codes. I can chose one, copy&paste it into the [my-next-app/src/app/page.txt](https://github.com/kazurayam/ReactInputField/blob/develop/my-next-app/src/app/page.tsx) and bring it in action at http://locahost:3000.

3. For example, I refered to the source of App.tsx at https://react.dev/learn/typescript#typing-dom-events, titled as "DOM Events", which demonstrates an `<input onChange="...">`. This could be the appropriate AUT to reproduce the "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)" issue.
