<p>
  <img src="https://github.com/rainbowgon/haruman/blob/main/upload/bg-1.png?raw=true">
  <img src="https://github.com/rainbowgon/haruman/blob/main/upload/bg-2.png?raw=true">
</p>

## ⭐ 프로젝트 소개

**하루 만 원으로 시작해보는 당신의 절약 습관, 하루만**

만원의 행복 아시나요? 일주일을 만 원으로 생활하는 도전 프로그램입니다. `하루만`은 이 프로그램에서 착안해서 만든 하루에 만 원 쓰기 챌린지입니다. 사용자는 하루 단위의 가벼운 챌린지를 통해 자연스럽게 절약을 체화할 수 있습니다. 또한 소비내역을 바탕으로 관심사에 기반한 이색 예적금 추천을 제공함으로써 금융을 더 친근하게 느낄 수 있도록 합니다.

**기획 배경**

- 한 달 단위의 예산 수립과 절약은 성취감을 느끼기 어려우며 무지출 챌린지는 지속하기 어려움
- 무지출 챌린지와 거지방 유행에서 알 수 있는 또래들의 놀이 문화가 된 절약하는 삶

**서비스 의도**

- 하루 단위의 챌린지를 통해 작은 돈의 절약으로도 성취감을 느낄 수 있음
- 줄일 수 있는 소비를 자연스럽게 알 수 있으며 현명한 소비습관을 체화할 수 있음
- 본인에게 친근한 예적금 상품을 통해 저축에 대한 진입장벽을 낮춤

**진행 일정**

- 개발 기간 : 2023.08.21 ~ 2023.10.06
- 배포 기간 : 2023.09.25 ~ 2023.10.15

> GitLab에서 프로젝트를 진행한 후, GitHub으로 옮겨왔습니다.

## ⭐ 프로젝트 주요 기능

## ⭐ 폴더 트리

```
frontend
├─ public
├─ src
│  ├─ apis
│  ├─ assets
│  │  ├─ icons
│  │  └─ image
│  ├─ components
│  │  ├─ Calender
│  │  ├─ DatePicker
│  │  └─ Save
│  ├─ constants
│  ├─ hooks
│  ├─ pages
│  ├─ stores
│  └─ styles
│     ├─ calendar
│     ├─ challenge
│     ├─ components
│     ├─ mypage
│     ├─ ranking
│     ├─ save
│     └─ user
```

```
backend
│  └─ src
│     ├─ main
│     │  ├─ java
│     │  │  └─ ssafy
│     │  │     └─ haruman
│     │  │        ├─ domain
│     │  │        │  ├─ category
│     │  │        │  │  ├─ controller
│     │  │        │  │  ├─ dto
│     │  │        │  │  │  ├─ request
│     │  │        │  │  │  └─ response
│     │  │        │  │  ├─ entity
│     │  │        │  │  ├─ repository
│     │  │        │  │  └─ service
│     │  │        │  ├─ challenge
│     │  │        │  ├─ deposit
│     │  │        │  ├─ member
│     │  │        │  └─ profile
│     │  │        └─ global
│     │  │           ├─ config
│     │  │           ├─ entity
│     │  │           ├─ error
│     │  │           ├─ filter
│     │  │           ├─ gpt
│     │  │           ├─ mattermost
│     │  │           ├─ response
│     │  │           ├─ service
│     │  │           └─ utils
│     │  └─ resources
│     └─ test
```

## ⭐ 시스템 아키텍처

<img src="https://github.com/rainbowgon/haruman/blob/main/upload/architecture.png?raw=true">

## ⭐ ERD

<img src="https://github.com/rainbowgon/haruman/blob/main/upload/erd.png?raw=true">

## ⭐ API 명세서

<img src="https://github.com/rainbowgon/haruman/blob/main/upload/api.png?raw=true">

## ⭐ 기술 스택

<h3 align="center">Frontend</h3>
<p align="center">
    <img src="https://img.shields.io/badge/Yarn-2C8EBB?&logo=yarn&logoColor=white">
    <img src="https://img.shields.io/badge/TypeScript-3178C6?&logo=typescript&logoColor=white">
    <img src="https://img.shields.io/badge/Node.js-339933?&logo=nodedotjs&logoColor=white">
    <img src="https://img.shields.io/badge/React-61DAFB?&logo=react&logoColor=white">
    <img src="https://img.shields.io/badge/PWA-5A0FC8?&logo=pwa&logoColor=white">
    <img src="https://img.shields.io/badge/Redux-764ABC?&logo=redux&logoColor=white">
    <img src="https://img.shields.io/badge/axios-5A29E4?&logo=axios&logoColor=white">
    <br>
    <img src="https://img.shields.io/badge/ReactRouter-CA4245?&logo=reactrouter&logoColor=white">
    <img src="https://img.shields.io/badge/ESLint-4B32C3?&logo=eslint&logoColor=white">
    <img src="https://img.shields.io/badge/Prettier-F7B93E?&logo=prettier&logoColor=white">
    <img src="https://img.shields.io/badge/Sass-CC6699?&logo=sass&logoColor=white">
    <img src="https://img.shields.io/badge/Chart.js-FF6384?&logo=chartdotjs&logoColor=white">
    <img src="https://img.shields.io/badge/D3.js-F9A03C?&logo=d3dotjs&logoColor=white">
</p>

<h3 align="center">Backend</h3>
<p align="center">
    <img src="https://img.shields.io/badge/Java-007396?&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/SpringBoot-6DB33F?&logo=springboot&logoColor=white">
    <img src="https://img.shields.io/badge/Gradle-02303A?&logo=gradle&logoColor=white">
    <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?&logo=springsecurity&logoColor=white">
    <img src="https://img.shields.io/badge/JWT-000000?&logo=jsonwebtokens&logoColor=white">
    <br>
    <img src="https://img.shields.io/badge/Hibernate-59666C?&logo=hibernate&logoColor=white">
    <img src="https://img.shields.io/badge/MySQL-4479A1?&logo=mysql&logoColor=white">
    <img src="https://img.shields.io/badge/Redis-DC382D?&logo=redis&logoColor=white">
    <img src="https://img.shields.io/badge/H2-FF9900?&logo=h2&logoColor=white">
    <img src="https://img.shields.io/badge/OpenAI-412991?&logo=openai&logoColor=white">
</p>

<h3 align="center">CI/CD</h3>
<p align="center">
    <img src="https://img.shields.io/badge/Docker-2496ED?&logo=docker&logoColor=white">
    <img src="https://img.shields.io/badge/Jenkins-D24939?&logo=jenkins&logoColor=white">
    <img src="https://img.shields.io/badge/nginx-009639?&logo=nginx&logoColor=white">
    <img src="https://img.shields.io/badge/SonarQube-4E9BCD?&logo=sonarqube&logoColor=white">
    <br>
    <img src="https://img.shields.io/badge/ubuntu-E95420?&logo=ubuntu&logoColor=white">
    <img src="https://img.shields.io/badge/amazon EC2-FF9900?&logo=amazon ec2&logoColor=white">
    <img src="https://img.shields.io/badge/amazon RDS-527FFF?&logo=amazonrds&logoColor=white">
    <img src="https://img.shields.io/badge/amazon S3-569A31?&logo=amazons3&logoColor=white">
</p>

<h3 align="center">Co-work tool</h3>
<p align="center">
    <img src="https://img.shields.io/badge/GitLab-FC6D26?&logo=GitLab&logoColor=white">
    <img src="https://img.shields.io/badge/Notion-000000?&logo=Notion&logoColor=white">
    <img src="https://img.shields.io/badge/Jira-0052CC?&logo=Jira Software&logoColor=white">
    <img src="https://img.shields.io/badge/Postman-FF6C37?&logo=Postman&logoColor=white">
    <img src="https://img.shields.io/badge/Figma-F24E1E?&logo=Figma&logoColor=white">
    <img src="https://img.shields.io/badge/Mattermost-0058CC?&logo=Mattermost&logoColor=white">
</p>

## ⭐ 멤버

<table>
  <tr>
  <td align="center">
      <a href="https://github.com/ng-lee">
        <img src="https://github.com/ng-lee.png" width="400" alt="이남곤" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/nutbrown">
        <img src="https://github.com/nutbrown.png" width="400" alt="전혜련" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/chogoal">
        <img src="https://github.com/chogoal.png" width="400" alt="최해미" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Byungul">
        <img src="https://github.com/Byungul.png" width="400" alt="변정원" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/usedupnote">
        <img src="https://github.com/usedupnote.png" width="400" alt="이정명" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Gobro-s">
        <img src="https://github.com/Gobro-s.png" width="400" alt="고세훈" />
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/ng-lee">
        <b>이남곤</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/nutbrown">
        <b>전혜련</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/chogoal">
        <b>최해미</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Byungul">
        <b>변정원</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/usedupnote">
        <b>이정명</b>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Gobro-s">
        <b>고세훈</b>
      </a>
    </td>
  </tr>
    <td align="center">
      <span>Backend</span>
    </td>
    <td align="center">
      <span>Backend<br>Infra</span>
    </td>
    <td align="center">
      <span>Backend</span>
    </td>
    <td align="center">
      <span>Backend</span>
    </td>
    <td align="center">
      <span>Frontend</span>
    </td>
    <td align="center">
      <span>Frontend</span>
    </td>
  </tr>
</table>
