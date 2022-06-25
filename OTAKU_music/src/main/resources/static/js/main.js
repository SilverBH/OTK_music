/*
  1:歌曲搜索接口
    请求地址:https://autumnfish.cn/search
    请求方法:get
    请求参数:keywords(查询关键字)
    响应内容:歌曲搜索结果

  2:歌曲url获取接口
    请求地址:https://autumnfish.cn/song/url × 现在要验证后才能调用
            https://music.163.com/song/media/outer/url?id=
    请求方法:get
    请求参数:id(歌曲id)
    响应内容:歌曲url地址
  3.歌曲详情获取
    请求地址:https://autumnfish.cn/song/detail
    请求方法:get
    请求参数:ids(歌曲id)
    响应内容:歌曲详情(包括封面信息)
  4.热门评论获取
    请求地址:https://autumnfish.cn/comment/hot?type=0
    请求方法:get
    请求参数:id(歌曲id,地址中的type固定为0)
    响应内容:歌曲的热门评论
  5.mv地址获取
    请求地址:https://autumnfish.cn/mv/url
    请求方法:get
    请求参数:id(mvid,为0表示没有mv)
    响应内容:mv的地址
  6.随机歌曲
    请求地址:https://api.uomg.com/api/rand.music
    请求方法:get/post
    请求参数:sort	    string	选填	  选择输出分类[热歌榜，新歌榜，飙升榜，抖音榜，电音榜]，为空输出热歌榜
            mid	    int	    选填	  网易云歌单ID
            format	string	选填	  选择输出格式
    响应内容:code	    string	      返回的状态码
            name	string	      歌曲名称
            url	    string	      歌曲链接
            picurl	string	      歌曲缩略图
   7.获取精品歌单 ×
     说明 : 调用此接口 , 可获取精品歌单
     可选参数 : cat: tag, 比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从精品歌单标签列表接口获取(/playlist/highquality/tags)
     limit: 取出歌单数量 , 默认为 20
     before: 分页参数,取上一页最后一个歌单的 updateTime 获取下一页数据
     接口地址 : /top/playlist/highquality
     调用例子 : /top/playlist/highquality?before=1503639064232&limit=3
   8.热搜列表(详细)
     说明 : 调用此接口,可获取热门搜索列表
     接口地址 : /search/hot/detail
     调用例子 : /search/hot/detail
   9.现在登陆后才能调用接口
     https://autumnfish.cn/login/cellphone?phone=xxx&password=yyy
*/
// 配置cookie
// import cookies from 'vue-cookies'
// Vue.prototype.$cookies = cookies;  //配置时候prototype.$这里的名字自己定义不是固定是cookies

var app = new Vue({
  el: "#player",
  data: {
    // 查询关键字
    query: "",
    // 歌曲名
    musicName: '',
    // 歌曲数组
    musicList: [],
    // 歌曲地址
    musicUrl: "",
    // 歌曲封面
    musicCover: "",
    // 歌曲评论
    hotComments: [],
    // 动画播放状态
    isPlaying: false,
    // 遮罩层的显示状态
    isShow: false,
    // mv地址
    mvUrl: ""
  },
  methods: {
    // 热歌推荐
    // randomMusic: function () {
    //   var that = this;
    //   axios.get("https://autumnfish.cn/search/hot/detail").then(
    //       function(response) {
    //         console.log(response);
    //         that.musicList = response.data.data.data;
    //         // console.log(response.data.result.songs);
    //       },
    //       function(err) {}
    //   );
    // },
    // 歌曲搜索
    searchMusic: function() {
      var that = this;
      axios.get("https://autumnfish.cn/search?keywords=" + this.query).then(
        function(response) {
          // console.log(response);
          that.musicList = response.data.result.songs;
          console.log(response.data.result.songs);
        },
        function(err) {}
      );
    },
    // 歌曲播放
    playMusic: function(musicId) {
      //   console.log(musicId);
      var that = this;
      // 获取歌曲地址
      axios.get("https://autumnfish.cn/song/url?id=" + musicId).then(
      // axios.get("https://music.163.com/song/media/outer/url?id=" + musicId).then(
      // axios.get("http://music.cyrilstudio.top/song/url?id=" + musicId).then(
        function(response) {
          // console.log(response);
          // console.log(response.data.data[0].url);
          // that.musicUrl = response.data.data[0].url;
          that.musicUrl = response.data.url;
        },
        function(err) {}
      );

      // 歌曲详情获取
      axios.get("https://autumnfish.cn/song/detail?ids=" + musicId).then(
        function(response) {
          // console.log(response);
          // console.log(response.data.songs[0].al.picUrl);
          that.musicCover = response.data.songs[0].al.picUrl;
        },
        function(err) {}
      );

      // 歌曲评论获取
      axios.get("https://autumnfish.cn/comment/hot?type=0&id=" + musicId).then(
        function(response) {
          // console.log(response);
          // console.log(response.data.hotComments);
          that.hotComments = response.data.hotComments;
        },
        function(err) {}
      );
    },
    // 歌曲播放
    play: function() {
      // console.log("play");
      this.isPlaying = true;
    },
    // 歌曲暂停
    pause: function() {
      // console.log("pause");
      this.isPlaying = false;
    },
    // 播放状态切换
    togglePlay: function() {
      if (this.isPlay) {
        this.$refs.audio.pause();
      } else {
        this.$refs.audio.play();
      }
    },
    // 播放mv
    playMV: function(mvid) {
      var that = this;
      axios.get("https://autumnfish.cn/mv/url?id=" + mvid).then(
        function(response) {
          // console.log(response);
          console.log(response.data.data.url);
          that.isShow = true;
          that.mvUrl = response.data.data.url;
        },
        function(err) {}
      );
    },
    // 隐藏
    hide: function() {
      this.isShow = false;
    }
  }
});
