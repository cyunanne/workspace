import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

// import Library from './chapter_03/Library';
// import Clock from './chapter_04/Clock';
// import CommentList from './chapter_05/CommentList';
// import NotificationList from './chapter_06/notificationList';
// import Accomodate from './chapter_07/accommodate';
// import ConfirmButton from './chapter_08/confirmButton';
// import LandingPage from './chapter_09/landingPage';
// import AttendanceBook from './chapter_10/attendanceBook';
// import SignUp from './chapter_11/signUp';
// import Calculator from './chapter_12/calculator';
import ProfileCard from './chapter_13/profileCard';

const root = ReactDOM.createRoot(document.getElementById('root'));

// chapter_03/Library
// root.render(
//   <React.StrictMode>
//     <Library />
//   </React.StrictMode>
// );

// chapter_04/Clock
// setInterval(() => {
//   root.render(
//     <React.StrictMode>
//       <Clock />
//     </React.StrictMode>
//   );
// }, 1000);

// chapter_05/CommentList
// root.render(
//   <React.StrictMode>
//     <CommentList />
//   </React.StrictMode>
// );

// chapter_06/NotificationList
// root.render(
//     <NotificationList />
// )

// chapter_07/Accomodate
// root.render(
//   <Accomodate />
// )

// chapter_08/ConfirmButton
// root.render(
//   <ConfirmButton/>
// )

// chapter_09/LandingPage
// root.render(
//   <LandingPage />
// )

// chapter_10/AttendanceBook
// root.render(
//   <AttendanceBook />
// )

// chapter_11/
// root.render(
//  <SignUp/>  
// )

// chapter_12/
// root.render(
//   <Calculator />
// )

// chapter_13/
root.render(
  <ProfileCard />
)

// chapter_14/

// chapter_15/


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();