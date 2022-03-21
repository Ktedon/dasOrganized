
$("#createChatButton").click(function(){
  $.get("/profileSearch/a", function(data, status){
    $("#searchResults").empty();
    data.forEach(function (value) {
      const archived = value.isArchived ? `<span class="text-sm font-medium bg-red-100 py-1 px-2 rounded text-red-500 align-middle m-2 text-center">Archived</span>` : `<span class="text-sm font-medium bg-green-100 py-1 px-2 rounded text-green-500 align-middle m-2 text-center">Activated</span>`
      $("#searchResults").append(
        `<div class="card border w-96 hover:shadow-none relative flex flex-col mx-auto shadow-lg m-5">
          <img class="max-h-20 w-full opacity-80 absolute top-0" style="z-index:-1; object-fit: cover;" src="assets/images/banners/${value.banner}" alt="" />
          <div class="profile w-full flex m-3 ml-4 text-white">
            <img class="w-28 h-28 p-1 bg-white rounded-full" src="${value.icon}" alt=""/>
            <div class="title mt-11 ml-3 font-bold flex flex-col">
              <div class="name break-words">${value.name}</div>
              ${archived}
            </div>
          </div>
          <div class="buttons flex absolute bottom-0 font-bold right-0 text-xs text-gray-500 space-x-0 my-3.5 mr-3">
            <button type="button" class="add border rounded-l-2xl rounded-r-sm border-gray-300 p-1 px-4 cursor-pointer hover:bg-gray-700 hover:text-white" x-on:click="addChatroomMember(${value.id}, '${value.name}', addMembers); addMembers.push(${value.id});">Add</button>
          </div>
        </div>`
      )
    });
  });
});


$("#userSearch").on("input", function(){
  $.get(`/profileSearch/${$(this).val()}`, function(data, status){
    $("#searchResults").empty();
    data.forEach(function (value) {
      const archived = value.isArchived ? `<span class="text-sm font-medium bg-red-100 py-1 px-2 rounded text-red-500 align-middle m-2 text-center">Archived</span>` : `<span class="text-sm font-medium bg-green-100 py-1 px-2 rounded text-green-500 align-middle m-2 text-center">Activated</span>`
      console.log(value)
      $("#searchResults").append(
        `<div class="card border w-96 hover:shadow-none relative flex flex-col mx-auto shadow-lg m-5">
          <img class="max-h-20 w-full opacity-80 absolute top-0" style="z-index:-1; object-fit: cover;" src="assets/images/banners/${value.banner}" alt="" />
          <div class="profile w-full flex m-3 ml-4 text-white">
            <img class="w-28 h-28 p-1 bg-white rounded-full" src="${value.icon}" alt=""/>
            <div class="title mt-11 ml-3 font-bold flex flex-col">
              <div class="name break-words">${value.name}</div>
              ${archived}
            </div>
          </div>
          <div class="buttons flex absolute bottom-0 font-bold right-0 text-xs text-gray-500 space-x-0 my-3.5 mr-3">
            <button type="button" class="add border rounded-l-2xl rounded-r-sm border-gray-300 p-1 px-4 cursor-pointer hover:bg-gray-700 hover:text-white" x-on:click="addChatroomMember(${value.id}, '${value.name}', addMembers); addMembers.push(${value.id});">Add</button>
          </div>
        </div>`
      )
    });
  });
});

function addChatroomMember(id, name, addMembers) {
  if (!addMembers.includes(id)) {
    $("#chatroomMembers").prepend(`<button type="button" id="chatrooomAddMember${id}" class="text-sm font-medium bg-red-100 py-1 px-2 rounded text-red-500 align-middle m-2 text-center" x-on:click="document.getElementById('chatrooomAddMember${id}').remove(); delete addMembers[addMembers.indexOf(${id})];">${name} ×</button>`)
  }
}

// function deleteChatroomMember(id) {
//   console.log(`chatrooomAddMember${id}`)
//   $(`chatrooomAddMember${id}`).fadeOut();
// }
