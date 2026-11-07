const API = process.env.EXPO_PUBLIC_API_URL ?? 'http://localhost:8080';
export async function api<T>(path:string, init:RequestInit={}, token?:string):Promise<T>{
 const response=await fetch(`${API}${path}`,{...init,headers:{'Content-Type':'application/json',...(token?{Authorization:`Bearer ${token}`}:{'X-User-Id':'demo-user'}),...init.headers}});
 if(!response.ok) throw new Error(await response.text() || `HTTP ${response.status}`);
 return response.json() as Promise<T>;
}
export async function uploadAsset(groupId:string,asset:{uri:string;name:string;type:string;size:number},token?:string){
 const ticket=await api<{mediaId:string;uploadUrl:string}>(`/api/groups/${groupId}/media/upload-url`,{method:'POST',body:JSON.stringify({fileName:asset.name,contentType:asset.type,sizeBytes:asset.size,capturedAt:new Date().toISOString()})},token);
 const blob=await (await fetch(asset.uri)).blob(); const put=await fetch(ticket.uploadUrl,{method:'PUT',headers:{'Content-Type':asset.type},body:blob}); if(!put.ok)throw new Error('Upload failed');
 await api(`/api/media/${ticket.mediaId}/complete`,{method:'POST'},token);
}
export {API};
