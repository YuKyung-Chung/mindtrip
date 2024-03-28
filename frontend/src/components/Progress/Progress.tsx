import { Progress } from "@nextui-org/react";

type Percent = {
  readonly percent : number
}

function MyProgress({ percent } : Percent ) {
  return (
    <Progress
      size="sm"
      radius="sm"
      classNames={{
        base: "max-w-md",
        track: "drop-shadow-md border border-default min-h-8",
        indicator: "bg-gradient-to-r from-pink-500 to-yellow-500",
        label: "tracking-wider font-medium text-default-600",
        value: "text-foreground/60",
      }}
      label="달성한 미션"
      value={percent} // API에서 받아온 percent 값으로 변경
      showValueLabel={true}
    />
  );
}

export default MyProgress;
