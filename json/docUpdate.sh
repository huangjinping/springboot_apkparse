#sh
cd '/Users/huhuijie/Downloads/dist'
echo "开始下载";
ls;
rm -rf 'inxdoc';
git clone  'https://gitee.com/weiluTech/inxdoc.git'
ls;
rm -rf res;
rm -rf src;
rm doc.html;
ls;
mv ./inxdoc/doc.html doc.html
mv ./inxdoc/res res
mv ./inxdoc/src  src
ls;