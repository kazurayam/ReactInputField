# Local Http server with a Nextjs/React-powered page, associated with E2E tests using Katalon Recorder, Katalon Studio and Playwright

[This GitHub repository](https://github.com/kazurayam/ReactInputField) contains 4 subprojects:

1. The [my-next-app](https://github.com/kazurayam/ReactInputField/tree/develop/my-next-app) suproject is a Next.js project that can launch a HTTP server with a React.js page at the URL of http://localhost:3000
2. The [testby_katalonrecorder](https://github.com/kazurayam/ReactInputField/tree/develop/testby_katalonrecorder) subproject contains a serialized test code for Katalon Recorder, that targets the URL lauched by the my-next-app project.
3. The [testby_katalonstudio](https://github.com/kazurayam/ReactInputField/tree/develop/testby_katalonstudio) subproject contains a Katalon Studio project that targets the same local URL.
4. The [testby_playwright](https://github.com/kazurayam/ReactInputField/tree/develop/testby_playwright) subproject is a [Playwright](https://playwright.dev/) project that targets the same local URL.

Provided that you have setup your PC with Node.js, Next.js, Katalon Recorder, Katalon Studio and Playwright, you can reproduce a problem discussed at the topic in the Katalon user forum:

- [Serious Recorder Bug - does not work with react](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)

I would refer to this topic as "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/143083)" for short.

## Problem to solve

In the "[KR+React issue](https://forum.katalon.com/t/serious-recorder-bug-does-not-work-with-react/14308/7)", @guy.mason wrote

>I personally use Katalon Recorder v7 with a React application, and it works for the greater majority of scenarios, but there is a particular text input field that where the field should automatically trigger an event when the contents changes, but presently doesn’t.

In the Katalon User Forum, we see a growing number of topis (questions and bug reports) about testing React apps. I find a common shortcoming in those topics. They talk about what they found in his/her own envionment without disclosing any information for others how to reproduce the incident on their own environment. The fundamental difficulty is that the UAT (*Application Under Test*) is running in his/her own private network, not public to the Internet, so that nobody else can get access to it. Without the UAT in action on our own PC in hands, we can never be productive enough in discussing the issues about testing React apps.






