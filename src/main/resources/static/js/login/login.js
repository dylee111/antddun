    window.onload = function() {
      document.getElementById('btnSubmit').onclick = function() {
        username = document.getElementById('username').value;
        password = document.getElementById('password').value;

        if (username == undefined || username == '') {
          alert('아이디를 확인해주세요');
          document.getElementById('username').focus();
          return;
        }
        if (password == undefined || password == '') {
          alert('비밀번호를 확인해주세요');
          document.getElementById('password').focus();
          return;
        }
        document.frm.submit();
      }
    }

