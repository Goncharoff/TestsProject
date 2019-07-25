<!DOCTYPE html>
<html>
  <head>
      <title>Welcome to Vue</title>
      <script src="https://unpkg.com/vue/dist/vue.js"></script>
  </head>
  <body>
      <div id="app">
          <img src="https://vuejs.org/images/logo.png" alt="Vue logo">
          <h1>{{ greeting }}</h1>
          <ul>
              <li>
              To learn more about Vue, visit
              <a :href="docsURL" target="_blank">
              {{ humanizeURL(docsURL) }}
              </a>
              </li>
              <li>
              For live help with simple questions, check out
              <a :href="gitterURL" target="_blank">
              the Gitter chat
              </a>
              </li>
              <li>
              For more complex questions, post to
              <a :href="forumURL" target="_blank">
              the forum
              </a>
              </li>
          </ul>
      </div>

      <script>
          new Vue({
              el: '#app',
              data: {
                  greeting: 'Welcome to your Vue.js app!',
                  docsURL: 'http://vuejs.org/guide/',
                  gitterURL: 'https://gitter.im/vuejs/vue',
                  forumURL: 'http://forum.vuejs.org/'
              },
              methods: {
                  humanizeURL: function (url) {
                      return url
                      .replace(/^https?:\/\//, '')
                      .replace(/\/$/, '')
                  }
              }
          })
      </script>
  </body>
</html>




<!-- <nav class="menu">
	<ul class="active">
		<li class="current-item"><a href="#">Home</a></li>
		<li><a href="#">My Work</a></li>
		<li><a href="#">About Me</a></li>
		<li><a href="#">Get in Touch</a></li>
		<li><a href="#">Blog</a></li>
	</ul>

	<a class="toggle-nav" href="#">&#9776;</a>

</nav> -->
